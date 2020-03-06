package com.totoro.server.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 更新用户信息DTO
 *
 * @author lwyang  2020/3/2
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDTO {

    private String nickname;

    private String password;

    private String avatar;

    private Byte sex;

    private LocalDate birthday;

    private String phone;

    private String email;

    private Byte vip;

    private String token;

    private LocalDateTime tokenExpireTime;

    private Integer points;

    private BigDecimal balance;

    private LocalDateTime lastLoginTime;


}
