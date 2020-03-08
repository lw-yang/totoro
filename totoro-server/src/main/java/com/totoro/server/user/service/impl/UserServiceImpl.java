package com.totoro.server.user.service.impl;

import com.totoro.common.response.ResultMessageEnum;
import com.totoro.common.utils.BCrypt;
import com.totoro.common.utils.CouponsCdKeyConstant;
import com.totoro.common.utils.RegExpConstant;
import com.totoro.db.dao.CouponMapper;
import com.totoro.db.dao.UserCouponMapper;
import com.totoro.db.dao.UserInfoMapper;
import com.totoro.db.entity.Coupon;
import com.totoro.db.entity.UserCoupon;
import com.totoro.db.entity.UserInfo;
import com.totoro.server.user.dto.LoginDTO;
import com.totoro.server.user.dto.RegisterDTO;
import com.totoro.server.user.dto.UpdateDTO;
import com.totoro.server.user.dto.UserDTO;
import com.totoro.server.user.enums.RegisterTypeEnum;
import com.totoro.server.user.exception.UserException;
import com.totoro.server.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 用户 Service
 *
 * @author lwyang  2020/2/27
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String USERNAME_PREFIX = "Totoro";

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    CouponMapper couponMapper;

    @Autowired
    UserCouponMapper userCouponMapper;

    @Override
    public UserDTO userLogin(LoginDTO loginDTO) {
        log.info("【userLogin Enter】请求参数loginDTO: {}", loginDTO.toString());

        UserInfo userInfo = null;
        String username = loginDTO.getUsername();

        // query the User
        switch (deduceRegisterType(username)) {
            case PHONE: {
                userInfo = userInfoMapper.selectByPhone(username);
                break;
            }
            case EMAIL: {
                userInfo = userInfoMapper.selectByEmail(username);
                break;
            }
            case USERNAME: {
                userInfo = userInfoMapper.selectByNickname(username);
                break;
            }
            default:
        }

        if (Objects.isNull(userInfo)) {
            log.error("【userLogin】用户不存在 username: {}", loginDTO.getUsername());
            throw new UserException(ResultMessageEnum.USER_NOT_EXIST);
        }

        // check user status
        if (userInfo.getStatus() == 0){
            log.error("【userLogin】用户已被禁用");
            throw new UserException(ResultMessageEnum.USER_STATUS_DISABLED);
        }

        // check password
        boolean isPasswordCorrect = BCrypt.checkpw(loginDTO.getPassword(), userInfo.getPassword());
        if (!isPasswordCorrect) {
            log.error("【userLogin】用户密码错误");
            throw new UserException(ResultMessageEnum.USER_PASSWORD_ERROR);
        }

        // generate token
        String token = UUID.randomUUID().toString().replaceAll("-", "");

        if (Objects.isNull(userInfo.getToken())) {
            userInfo.setToken(token);
        }

        LocalDateTime now = LocalDateTime.now();
        userInfo.setTokenExpireTime(now.plusDays(1L));
        userInfo.setLastLoginTime(now);

        // update the token and tokenExpireTime
        UserInfo userUpdateInfo = new UserInfo();
        userUpdateInfo.setId(userInfo.getId());
        if (Objects.isNull(userInfo.getToken())){
            userUpdateInfo.setToken(token);
        }
        userUpdateInfo.setTokenExpireTime(now.plusDays(1L));
        userUpdateInfo.setLastLoginTime(now);

        int updateResult = userInfoMapper.updateByPrimaryKeySelective(userUpdateInfo);
        if (updateResult != 1) {
            log.error("【userLogin】用户token更新失败");
            throw new UserException(ResultMessageEnum.USER_UPDATE_FAILURE);
        }

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userInfo, userDTO);

        // set couponsCount
        int couponsCount = couponMapper.selectCountByUserId(userInfo.getId());
        userDTO.setCouponsCount(couponsCount);

        // TODO set browse history

        log.info("【userLogin Exit】userDTO: {}", userDTO.toString());
        return userDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional register(RegisterDTO registerDTO) {
        log.info("【register Enter】请求参数registerDTO: {}", registerDTO.toString());

        //check password
        if (!Objects.equals(registerDTO.getPassword(), registerDTO.getRepeatPassword())) {
            log.error("【register】用户注册两次密码不一致");
            throw new UserException(ResultMessageEnum.USER_PASSWORD_NOT_SAME);
        }

        UserInfo userInfo = new UserInfo();
        String registerUsername = registerDTO.getUsername();

        //set username
        switch (deduceRegisterType(registerUsername)) {
            case EMAIL: {
                if (Objects.nonNull(userInfoMapper.selectByEmail(registerUsername))) {
                    log.error("【register】用户注册邮箱已存在: {}", registerUsername);
                    throw new UserException(ResultMessageEnum.USER_EMAIL_EXIST);
                }

                userInfo.setEmail(registerUsername);
                userInfo.setNickname(USERNAME_PREFIX + UUID.randomUUID().toString()
                        .replaceAll("-", "@").substring(0, 10));
                break;
            }
            case PHONE:
                if (Objects.nonNull(userInfoMapper.selectByPhone(registerUsername))) {
                    log.error("【register】用户注册手机号已存在: {}", registerUsername);
                    throw new UserException(ResultMessageEnum.USER_PHONE_EXIST);
                }

                userInfo.setPhone(registerUsername);
                userInfo.setNickname(USERNAME_PREFIX + UUID.randomUUID().toString()
                        .replaceAll("-", "#").substring(0, 10));
                break;
            case USERNAME:
                if (Objects.nonNull(userInfoMapper.selectByNickname(registerUsername))) {
                    log.error("【register】用户注册用户名已存在: {}", registerUsername);
                    throw new UserException(ResultMessageEnum.USER_NAME_EXIST);
                }

                userInfo.setNickname(registerUsername);
                break;
            default:
        }

        //set password
        String bCryptPassword = BCrypt.hashpw(registerDTO.getPassword());
        userInfo.setPassword(bCryptPassword);

        //init
        userInfo.setBalance(new BigDecimal("0.00"));
        userInfo.setPoints(0);
        userInfo.setVip((byte) 0);
        userInfo.setCreateTime(LocalDateTime.now());

        //database insert
        int insertResult = userInfoMapper.insertSelective(userInfo);
        if (insertResult != 1) {
            log.error("【register】用户注册失败 username: {}", registerUsername);
            throw new UserException(ResultMessageEnum.USER_REGISTER_FAILURE);
        }

        //generate coupon
        generateCoupons(userInfo.getId());

        log.info("【register Exit");
        return Optional.empty();
    }

    /**
     * 为新注册用户生成优惠券
     */
    private void generateCoupons(Long userId) {
        Coupon v10c0 = couponMapper.selectByCdKey(CouponsCdKeyConstant.VALUE_10_CONDITION_0);
        if (Objects.isNull(v10c0)) {
            Coupon coupon = new Coupon();
            coupon.setName("10元新人优惠券");
            coupon.setUseCondition(0);
            coupon.setEffectiveTime(LocalDate.now());
            coupon.setExpireTime(LocalDate.now().plusDays(3));
            coupon.setValue(10);
            coupon.setCreateTime(LocalDateTime.now());
            coupon.setCdKey(CouponsCdKeyConstant.VALUE_10_CONDITION_0);
            int insertResult = couponMapper.insertSelective(coupon);
            if (insertResult != 1) {
                log.error("【generateCoupons】生成10元优惠券失败");
                throw new UserException(ResultMessageEnum.COUPON_USER_INSERT_FAILURE);
            }
            log.info("【generateCoupons】生成10元优惠券");
            insertUserCoupon(userId, coupon.getId());
        } else {
            insertUserCoupon(userId, v10c0.getId());
        }
        Coupon v20c50 = couponMapper.selectByCdKey(CouponsCdKeyConstant.VALUE_20_CONDITION_50);
        if (Objects.isNull(v20c50)) {
            Coupon coupon = new Coupon();
            coupon.setName("20元新人优惠券");
            coupon.setUseCondition(50);
            coupon.setEffectiveTime(LocalDate.now());
            coupon.setExpireTime(LocalDate.now().plusDays(7));
            coupon.setValue(20);
            coupon.setCreateTime(LocalDateTime.now());
            coupon.setCdKey(CouponsCdKeyConstant.VALUE_20_CONDITION_50);
            int insertResult = couponMapper.insertSelective(coupon);
            if (insertResult != 1) {
                log.error("【generateCoupons】生成20元优惠券失败");
                throw new UserException(ResultMessageEnum.COUPON_USER_INSERT_FAILURE);
            }
            log.info("【generateCoupons】生成20元优惠券");

            insertUserCoupon(userId, coupon.getId());
        } else {
            insertUserCoupon(userId, v20c50.getId());
        }
        Coupon v30c100 = couponMapper.selectByCdKey(CouponsCdKeyConstant.VALUE_30_CONDITION_100);
        if (Objects.isNull(v30c100)) {
            Coupon coupon = new Coupon();
            coupon.setName("30元新人优惠券");
            coupon.setUseCondition(100);
            coupon.setEffectiveTime(LocalDate.now());
            coupon.setExpireTime(LocalDate.now().plusDays(30));
            coupon.setValue(30);
            coupon.setCreateTime(LocalDateTime.now());
            coupon.setCdKey(CouponsCdKeyConstant.VALUE_30_CONDITION_100);
            int insertResult = couponMapper.insertSelective(coupon);
            if (insertResult != 1) {
                log.error("【generateCoupons】生成30元优惠券失败");
                throw new UserException(ResultMessageEnum.COUPON_USER_INSERT_FAILURE);
            }
            log.info("【generateCoupons】生成30元优惠券");

            insertUserCoupon(userId, coupon.getId());
        } else {
            insertUserCoupon(userId, v30c100.getId());
        }
    }

    private void insertUserCoupon(Long userId, Long couponId) {
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setCouponId(couponId);
        userCoupon.setUserId(userId);
        userCoupon.setCreateTime(LocalDateTime.now());
        int insertResult = userCouponMapper.insertSelective(userCoupon);
        if (insertResult != 1) {
            log.error("【insertUserCoupon】插入用户-优惠券表失败");
            throw new UserException(ResultMessageEnum.COUPON_USER_INSERT_FAILURE);
        }
    }

    /**
     * 推断用户注册类型
     *
     * @param registerUsername registerUsername
     * @return RegisterTypeEnum
     * @see RegisterTypeEnum
     */
    private RegisterTypeEnum deduceRegisterType(String registerUsername) {
        if (Pattern.matches(RegExpConstant.EMAIL, registerUsername)) {
            return RegisterTypeEnum.EMAIL;
        } else if (Pattern.matches(RegExpConstant.PHONE, registerUsername)) {
            return RegisterTypeEnum.PHONE;
        } else {
            return RegisterTypeEnum.USERNAME;
        }
    }

    @Override
    public UserDTO getUserByToken(String token) {
        log.info("【getUserByToken Enter】请求参数token: {}", token);

        // query user
        UserInfo userInfo = userInfoMapper.selectByToken(token);

        if (Objects.isNull(userInfo)) {
            log.error("【getUserByToken】用户不存在 token: {}", token);
            throw new UserException(ResultMessageEnum.USER_NOT_EXIST);
        }

        // check user status
        if (userInfo.getStatus() == 0){
            log.error("【getUserByToken】用户已被禁用");
            throw new UserException(ResultMessageEnum.USER_STATUS_DISABLED);
        }

        //token expire
        if (userInfo.getTokenExpireTime().isBefore(LocalDateTime.now())) {
            log.error("【getUserByToken】用户token已失效 token: {}", token);
            throw new UserException(ResultMessageEnum.USER_TOKEN_EXPIRE);
        }

        //update token and login time
        UserInfo userInfoUpdate = new UserInfo();
        userInfoUpdate.setId(userInfo.getId());
        userInfoUpdate.setTokenExpireTime(LocalDateTime.now().plusDays(1L));
        userInfoUpdate.setLastLoginTime(LocalDateTime.now());
        int updateResult = userInfoMapper.updateByPrimaryKeySelective(userInfoUpdate);
        if (updateResult != 1) {
            log.error("【getUserByToken】更新用户token和登录时间失败");
            throw new UserException(ResultMessageEnum.USER_UPDATE_FAILURE);
        }

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userInfo, userDTO);

        // set couponsCount
        int couponsCount = couponMapper.selectCountByUserId(userInfo.getId());
        userDTO.setCouponsCount(couponsCount);

        //TODO set browse history

        log.info("【getUserByToken Exit】 userDTO: {}", userDTO.toString());
        return userDTO;
    }

    @Override
    public Optional updateUser(UpdateDTO updateDTO, Long id) {
        log.info("【updateUser Enter】请求参数updateDTO: {}", updateDTO.toString());

        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(updateDTO, userInfo);
        userInfo.setId(id);
        int updateResult = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        if (updateResult != 1) {
            log.error("【updateUser】更新用户信息失败");
            throw new UserException(ResultMessageEnum.USER_UPDATE_FAILURE);
        }

        log.info("【updateUser Exit");
        return Optional.empty();
    }
}
