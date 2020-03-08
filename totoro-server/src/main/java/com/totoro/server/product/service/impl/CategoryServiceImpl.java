package com.totoro.server.product.service.impl;

import com.totoro.db.dao.CategoryMapper;
import com.totoro.db.entity.Category;
import com.totoro.server.product.dto.CategoryDTO;
import com.totoro.server.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lwyang  2020/3/8
 */

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORY_QUERY_RECOMMEND = "recommend";

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> listCategories(String query, Long parentId){
        List<Category> categoryList = new ArrayList<>();

        if (Objects.nonNull(query)){
            if (CATEGORY_QUERY_RECOMMEND.equals(query)){

                // query recommend category
                categoryList = categoryMapper.selectByRecommend();
            }
        }else {

            // query category with parentId
            categoryList = categoryMapper.selectByParentId(parentId);
        }

        // if (Objects.isNull(categoryList)){
        //     log.error("【listCategories】 商品类别查询失败");
        //     throw new ProductException(ResultMessageEnum.CATEGORY_SELECT_FAILURE);
        // }

        List<CategoryDTO> categoryDTOList = new ArrayList<>(categoryList.size());

        categoryList.forEach(i -> {
            CategoryDTO categoryDTO = new CategoryDTO();
            BeanUtils.copyProperties(i, categoryDTO);
            categoryDTOList.add(categoryDTO);
        });

        return categoryDTOList;
    }
}
