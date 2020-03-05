package com.totoro.server.user.service.impl;

import com.totoro.common.response.ResultMessageEnum;
import com.totoro.common.utils.BCrypt;
import com.totoro.common.utils.RegExpConstant;
import com.totoro.db.dao.CouponMapper;
import com.totoro.db.dao.UserInfoMapper;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

/**
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

    @Override
    public UserDTO userLogin(LoginDTO loginDTO) {

        log.info("【userLogin】请求参数loginDTO：{}", loginDTO.toString());

        UserInfo userInfo = null;
        String username = loginDTO.getUsername();

        //query the User
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

        //check password
        boolean isPasswordCorrect = BCrypt.checkpw(loginDTO.getPassword(), userInfo.getPassword());
        if (!isPasswordCorrect) {
            log.error("【userLogin】用户密码错误");
            throw new UserException(ResultMessageEnum.USER_PASSWORD_ERROR);
        }

        //generate token
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        userInfo.setToken(token);

        LocalDateTime now = LocalDateTime.now();
        userInfo.setTokenExpireTime(now.plusDays(1L));
        userInfo.setLastLoginTime(now);

        //store the token
        UserInfo userUpdateInfo = new UserInfo();
        userUpdateInfo.setId(userInfo.getId());
        userUpdateInfo.setToken(token);
        userUpdateInfo.setTokenExpireTime(now.plusDays(1L));
        userUpdateInfo.setLastLoginTime(now);

        int updateResult = userInfoMapper.updateByPrimaryKeySelective(userUpdateInfo);
        if (updateResult != 1) {
            log.error("【userLogin】用户token更新失败");
            throw new UserException(ResultMessageEnum.USER_UPDATE_FAILURE);
        }

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userInfo, userDTO);

        int couponsCount = couponMapper.selectCountByUserId(userInfo.getId());
        userDTO.setCouponsCount(couponsCount);

        //TODO set browse history

        return userDTO;
    }

    @Override
    public Optional register(RegisterDTO registerDTO) {

        log.info("【register】请求参数registerDTO：{}", registerDTO.toString());

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
                    log.error("【register】用户注册邮箱已存在：{}", registerUsername);
                    throw new UserException(ResultMessageEnum.USER_EMAIL_EXIST);
                }

                userInfo.setEmail(registerUsername);
                userInfo.setNickname(USERNAME_PREFIX + UUID.randomUUID().toString()
                        .replaceAll("-", "@").substring(0, 10));
                break;
            }
            case PHONE:
                if (Objects.nonNull(userInfoMapper.selectByPhone(registerUsername))) {
                    log.error("【register】用户注册手机号已存在：{}", registerUsername);
                    throw new UserException(ResultMessageEnum.USER_PHONE_EXIST);
                }

                userInfo.setPhone(registerUsername);
                userInfo.setNickname(USERNAME_PREFIX + UUID.randomUUID().toString()
                        .replaceAll("-", "#").substring(0, 10));
                break;
            case USERNAME:
                if (Objects.nonNull(userInfoMapper.selectByNickname(registerUsername))) {
                    log.error("【register】用户注册用户名已存在：{}", registerUsername);
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

        return Optional.empty();
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
    public UserDTO getUserByToken(String token){
        log.info("【getUserByToken】请求参数token：{}", token);

        UserInfo userInfo = userInfoMapper.selectByToken(token);

        if (Objects.isNull(userInfo)){
            log.error("【getUserByToken】用户不存在 token：{}", token);
            throw new UserException(ResultMessageEnum.USER_NOT_EXIST);
        }

        //token expire
        if (userInfo.getTokenExpireTime().isBefore(LocalDateTime.now())){
            log.error("【getUserByToken】用户token已失效 token：{}", token);
            throw new UserException(ResultMessageEnum.USER_TOKEN_EXPIRE);
        }

        //update token and login time
        UserInfo userInfoUpdate = new UserInfo();
        userInfoUpdate.setId(userInfo.getId());
        userInfoUpdate.setTokenExpireTime(LocalDateTime.now().plusDays(1L));
        userInfoUpdate.setLastLoginTime(LocalDateTime.now());
        int updateResult = userInfoMapper.updateByPrimaryKeySelective(userInfoUpdate);
        if (updateResult != 1){
            log.error("【getUserByToken】更新用户token和登录时间失败");
            throw new UserException(ResultMessageEnum.USER_UPDATE_FAILURE);
        }

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userInfo, userDTO);

        int couponsCount = couponMapper.selectCountByUserId(userInfo.getId());
        userDTO.setCouponsCount(couponsCount);

        //TODO set browse history

        return userDTO;
    }

    @Override
    public Optional updateUser(UpdateDTO updateDTO){
        log.info("【updateUser】请求参数updateDTO：{}", updateDTO.toString());

        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(updateDTO, userInfo);
        int updateResult = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        if (updateResult != 1){
            log.error("【updateUser】更新用户信息失败");
            throw new UserException(ResultMessageEnum.USER_UPDATE_FAILURE);
        }

        return Optional.empty();
    }
}
