package com.totoro.server.user.service;

import com.totoro.server.user.dto.LoginDTO;
import com.totoro.server.user.dto.RegisterDTO;
import com.totoro.server.user.dto.UserDTO;

import java.util.Optional;

/**
 * @author lwyang  2020/2/27
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param loginDTO
     * @return
     */
    UserDTO userLogin(LoginDTO loginDTO);

    Optional register(RegisterDTO registerDTO);
}
