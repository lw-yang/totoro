package com.totoro.server.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author lwyang  2020/2/29
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;

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

    private Integer couponsCount;

    //TODO browse history list
}
