package com.totoro.server.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 注册信息DTO
 *
 * @author lwyang  2020/2/29
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDTO {

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;

    @NotNull(message = "重复输入的密码不能为空")
    private String repeatPassword;
}
