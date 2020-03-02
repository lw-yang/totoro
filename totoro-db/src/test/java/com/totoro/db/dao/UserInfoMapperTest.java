package com.totoro.db.dao;

import com.totoro.db.entity.UserInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
class UserInfoMapperTest {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Test
    void deleteByPrimaryKey() {
    }

    @Test
    void insertSelective() {
        UserInfo userInfo = new UserInfo();
        userInfo.setNickname("lwyang");
        userInfo.setAvatar("src_img");
        userInfo.setBalance(new BigDecimal("54.19"));
        userInfo.setBirthday(LocalDate.now());
        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setVip(new Byte("1"));
        userInfo.setPhone("12345678910");
        int result = userInfoMapper.insertSelective(userInfo);
        Assertions.assertEquals(1,result);
    }

    @Test
    void selectByPrimaryKey() {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1L);
        System.out.println(userInfo);
    }

    @Test
    void updateByPrimaryKeySelective() {
    }

    @Test
    void updateByPrimaryKey() {
    }
}