package com.totoro.server.advertise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author lwyang  2020/3/21
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyChoiceDTO {
    private Long id;

    private Long productId;

    private String productDesc;

    private String choiceTag;

    private String productName;

    private BigDecimal productPrice;
}
