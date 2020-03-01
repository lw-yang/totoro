package com.totoro.server.user.controller;

import com.totoro.common.response.Result;
import com.totoro.server.user.dto.LoginDTO;
import com.totoro.server.user.dto.RegisterDTO;
import com.totoro.server.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User Controller
 *
 * @author lwyang  2020/2/27
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户登录
     * @param loginDTO 登录信息
     * @return Result [status: 201]
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDTO loginDTO){
        return Result.success(userService.userLogin(loginDTO));
    }

    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterDTO registerDTO){
        return Result.created(userService.register(registerDTO));
    }

}
