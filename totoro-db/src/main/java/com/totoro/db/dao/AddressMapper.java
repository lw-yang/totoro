package com.totoro.db.dao;

import com.totoro.db.entity.Address;

import java.util.List;

public interface AddressMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @mbg.generated
     */
    int insert(Address record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @mbg.generated
     */
    int insertSelective(Address record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @mbg.generated
     */
    Address selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Address record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table address
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Address record);



    /**
     * 根据 userId 获取Address列表
     *
     * @param userId
     * @return
     */
    List<Address> selectByUserId(Long userId);

    /**
     * 更新地址不为默认地址
     *
     * @param userId
     * @return
     */
    int updateNotDefaultByPrimaryKey(Long userId);
}