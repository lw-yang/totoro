package com.totoro.db.dao;

import com.totoro.db.entity.Category;

import java.util.List;

public interface CategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbg.generated
     */
    int insert(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbg.generated
     */
    int insertSelective(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbg.generated
     */
    Category selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table category
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Category record);



    /**
     * 查询所有推荐类别
     *
     * @return List<Category>
     */
    List<Category> selectByRecommend();

    /**
     * 查询所有首页分类
     *
     * @return List<Category>
     */
    List<Category> selectByHome();

    /**
     * 根据 parentId 查询所有类别
     *
     * @param parentId parentId
     * @return List<Category>
     */
    List<Category> selectByParentId(Long parentId);
}