package com.totoro.server.user.service.impl;

import com.totoro.common.response.ResultMessageEnum;
import com.totoro.common.utils.BCrypt;
import com.totoro.common.utils.RegExpConstant;
import com.totoro.db.dao.UserInfoMapper;
import com.totoro.db.entity.UserInfo;
import com.totoro.server.user.dto.LoginDTO;
import com.totoro.server.user.dto.RegisterDTO;
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

    public static final String USERNAME_PREFIX = "Totoro";

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public UserDTO userLogin(LoginDTO loginDTO) {

        UserInfo userInfo = null;
        String username = loginDTO.getUsername();

        //query the User
        switch (deduceRegisterType(username)) {
            case PHONE:{
                userInfo = userInfoMapper.selectByPhone(username);
                break;
            }
            case EMAIL:{
                userInfo = userInfoMapper.selectByEmail(username);
                break;
            }
            case USERNAME:{
                userInfo = userInfoMapper.selectByUsername(username);
                break;
            }
            default:
        }

        if (Objects.isNull(userInfo)) {
            log.error("【用户不存在】 username: {}", loginDTO.getUsername());
            throw new UserException(ResultMessageEnum.USER_NOT_EXIST);
        }

        //check password
        boolean isPasswordCorrect = BCrypt.checkpw(loginDTO.getPassword(), userInfo.getPassword());
        if (!isPasswordCorrect) {
            log.error("【用户密码错误】");
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
            log.error("【用户token更新失败】");
            throw new UserException(ResultMessageEnum.USER_UPDATE_FAILURE);
        }

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userInfo, userDTO);

        //TODO set browse history

        return userDTO;
    }

    @Override
    public Optional register(RegisterDTO registerDTO) {

        if (!Objects.equals(registerDTO.getPassword(), registerDTO.getRepeatPassword())) {
            log.error("【用户注册两次密码不一致】");
            throw new UserException(ResultMessageEnum.USER_PASSWORD_NOT_SAME);
        }

        UserInfo userInfo = new UserInfo();
        String registerUsername = registerDTO.getUsername();

        switch (deduceRegisterType(registerUsername)) {
            case EMAIL: {
                userInfo.setEmail(registerUsername);
                userInfo.setNickname(USERNAME_PREFIX + UUID.randomUUID().toString()
                        .replaceAll("-","@").substring(0, 10));
                break;
            }
            case PHONE:
                userInfo.setPhone(registerUsername);
                userInfo.setNickname(USERNAME_PREFIX + UUID.randomUUID().toString()
                        .replaceAll("-","#").substring(0, 10));
                break;
            case USERNAME:
                userInfo.setNickname(registerUsername);
                break;
            default:
        }

        String bCryptPassword = BCrypt.hashpw(registerDTO.getPassword());
        userInfo.setPassword(bCryptPassword);

        userInfo.setBalance(new BigDecimal("0.00"));
        userInfo.setPoints(0);
        userInfo.setVip((byte) 0);
        userInfo.setCreateTime(LocalDateTime.now());

        int insertResult = userInfoMapper.insertSelective(userInfo);
        if (insertResult != 1){
            log.error("【用户注册失败】 username: {}" , registerUsername);
            throw new UserException(ResultMessageEnum.USER_REGISTER_FAILURE);
        }

        return Optional.empty();
    }

    private RegisterTypeEnum deduceRegisterType(String registerUsername) {
        if (Pattern.matches(RegExpConstant.EMAIL, registerUsername)) {
            return RegisterTypeEnum.EMAIL;
        } else if (Pattern.matches(RegExpConstant.PHONE, registerUsername)) {
            return RegisterTypeEnum.PHONE;
        } else {
            return RegisterTypeEnum.USERNAME;
        }
    }
}
