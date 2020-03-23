package com.totoro.server.product.controller;

import com.totoro.common.response.Result;
import com.totoro.server.product.dto.CategoryDTO;
import com.totoro.server.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分类 Controller
 *
 * @author lwyang  2020/3/8
 */

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public Result<List<CategoryDTO>> listCategories(@RequestParam(value = "q", required = false) String query,
                                                    @RequestParam(value = "parentId",defaultValue = "-1") String parentId){
        return  Result.success(categoryService.listCategories(query, Long.valueOf(parentId)));
    }
}
