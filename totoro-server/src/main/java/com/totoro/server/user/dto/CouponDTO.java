package com.totoro.server.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 优惠券 DTO
 *
 * @author lwyang  2020/3/5
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponDTO {
    private Long id;

    private String name;

    private Integer value;

    private Integer useCondition;

    private Byte status;

    private Long effectiveTime;

    private Long expireTime;
}
