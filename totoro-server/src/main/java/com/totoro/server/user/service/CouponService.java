package com.totoro.server.user.service;

import com.totoro.server.user.dto.CouponDTO;

import java.util.List;
import java.util.Map;

/**
 * @author lwyang  2020/3/5
 */
public interface CouponService {
    /**
     * 返回用户的优惠券
     *
     * @param userId userId
     * @return List<CouponDTO
     */
    Map<String, List<CouponDTO>> listCoupons(Long userId);
}
