package com.totoro.server.user.service;

import com.totoro.server.user.dto.LoginDTO;
import com.totoro.server.user.dto.RegisterDTO;
import com.totoro.server.user.dto.UpdateDTO;
import com.totoro.server.user.dto.UserDTO;

import java.util.Optional;

/**
 * @author lwyang  2020/2/27
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param loginDTO loginDTO
     * @return UserDTO
     */
    UserDTO userLogin(LoginDTO loginDTO);

    /**
     * 用户注册
     *
     * @param registerDTO registerDTO
     * @return Optional
     */
    Optional register(RegisterDTO registerDTO);

    /**
     * 根据token获取用户信息
     *
     * @param token token
     * @return UserDTO
     */
    UserDTO getUserByToken(String token);

    /**
     * 更新用户信息
     *
     * @param updateDTO UpdateDTO
     * @param id id
     * @return Optional
     */
    Optional updateUser(UpdateDTO updateDTO, Long id);
}
