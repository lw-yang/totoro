package com.totoro.server.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类 DTO
 *
 * @author lwyang  2020/3/8
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {

    private Long id;

    private String name;

    private String img;

    private Long parentId;

    private Byte status;

    private Byte gender;

    private Byte recommend;
}
