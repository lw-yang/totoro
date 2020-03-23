package com.totoro.server.product.service;

import com.totoro.server.product.dto.CategoryDTO;

import java.util.List;

/**
 * @author lwyang  2020/3/8
 */
public interface CategoryService {

    /**
     * 查询商品类别
     *
     * @param query 查询条件
     * @param parentId 类别父级 id
     * @return List<CategoryDTO>
     */
    List<CategoryDTO> listCategories(String query, Long parentId);
}
