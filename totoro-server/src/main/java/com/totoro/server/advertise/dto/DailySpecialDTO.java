package com.totoro.server.advertise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 每日特价商品 DTO
 *
 * @author lwyang  2020/3/21
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailySpecialDTO {

    private Long id;

    private Long productId;

    private BigDecimal originPrice;

    private BigDecimal discountPrice;

    private LocalDate effectiveDate;

    private String productThumb;
}
