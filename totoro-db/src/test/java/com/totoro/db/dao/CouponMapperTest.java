package com.totoro.db.dao;

import com.totoro.db.entity.Coupon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CouponMapperTest {

    @Autowired
    CouponMapper couponMapper;

    @Test
    void listCouponsByUserId() {
        List<Coupon> couponList = couponMapper.listCouponsByUserId(12L);
        System.out.println(couponList);
    }

    @Test
    void selectByCdKey() {
        Coupon coupon = couponMapper.selectByCdKey("VALUE_10_CONDITION_0");
        System.out.println(coupon);
    }
}