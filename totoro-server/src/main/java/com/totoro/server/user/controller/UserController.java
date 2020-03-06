package com.totoro.server.user.controller;

import com.totoro.common.response.Result;
import com.totoro.server.user.dto.LoginDTO;
import com.totoro.server.user.dto.RegisterDTO;
import com.totoro.server.user.dto.UpdateDTO;
import com.totoro.server.user.dto.UserDTO;
import com.totoro.server.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * User Controller
 *
 * @author lwyang  2020/2/27
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户登录
     *
     * @param loginDTO 登录信息
     * @return Result [status: 200]
     */
    @PostMapping("/login")
    public Result<UserDTO> login(@Validated @RequestBody LoginDTO loginDTO) {
        return Result.success(userService.userLogin(loginDTO));
    }

    /**
     * 用户注册
     *
     * @param registerDTO 注册信息
     * @return Result [status: 201]
     */
    @PostMapping("/register")
    public Result<Optional> register(@Validated @RequestBody RegisterDTO registerDTO) {
        return Result.created(userService.register(registerDTO));
    }

    /**
     * 根据token获取用户信息
     *
     * @param token token
     * @return Result [status: 200]
     */
    @GetMapping("/{token}")
    public Result<UserDTO> getUser(@PathVariable String token) {
        return Result.success(userService.getUserByToken(token));
    }

    /**
     * 更新用户信息
     *
     * @param updateDTO updateDTO
     * @return Result [status: 200]
     */
    @PutMapping("/{id}")
    public Result<Optional> updateUser(@RequestBody UpdateDTO updateDTO, @PathVariable Long id) {
        return Result.success(userService.updateUser(updateDTO, id));
    }

}
