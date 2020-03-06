package com.totoro.server.user.controller;

import com.totoro.common.response.Result;
import com.totoro.server.user.dto.CouponDTO;
import com.totoro.server.user.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 优惠券 Controller
 *
 * @author lwyang  2020/3/5
 */

@RestController
@RequestMapping("/users")
public class CouponController {

    @Autowired
    CouponService couponService;

    @GetMapping("/{id}/coupons")
    public Result<Map<String, List<CouponDTO>>> listCoupons(@PathVariable Long id){
        return Result.success(couponService.listCoupons(id));
    }
}
