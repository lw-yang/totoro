package com.totoro.server.user.service.impl;

import com.totoro.db.dao.CouponMapper;
import com.totoro.db.entity.Coupon;
import com.totoro.server.user.dto.CouponDTO;
import com.totoro.server.user.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 优惠券 Service
 *
 * @author lwyang  2020/3/5
 */

@Service
@Slf4j
public class CouponServiceImpl implements CouponService {

    private static final String AVAILABLE_COUPONS = "availableCoupons";
    private static final String EXPIRED_COUPONS = "expiredCoupons";

    @Autowired
    CouponMapper couponMapper;

    @Override
    public Map<String, List<CouponDTO>> listCoupons(Long userId){
        log.info("【listCoupons Enter】 userId: {}", userId);

        List<Coupon> couponList = couponMapper.listCouponsByUserId(userId);

        List<CouponDTO> couponAvailableDTOList = new ArrayList<>();
        List<CouponDTO> couponExpiredDTOList = new ArrayList<>();

        couponList.forEach(i -> {
            CouponDTO couponDTO = new CouponDTO();
            BeanUtils.copyProperties(i, couponDTO);
            couponDTO.setEffectiveTime(LocalDateTime.of(i.getEffectiveTime(), LocalTime.MIDNIGHT).toEpochSecond(ZoneOffset.of("+8")));
            couponDTO.setExpireTime(LocalDateTime.of(i.getExpireTime(), LocalTime.MIDNIGHT).toEpochSecond(ZoneOffset.of("+8")));
            if (i.getExpireTime().plusDays(1L).isAfter(LocalDate.now())){
                couponAvailableDTOList.add(couponDTO);
            }else {
                couponExpiredDTOList.add(couponDTO);
            }
        });

        Map<String, List<CouponDTO>> map = new HashMap<>(2, 1);
        map.put(AVAILABLE_COUPONS, couponAvailableDTOList);
        map.put(EXPIRED_COUPONS, couponExpiredDTOList);

        log.info("【listCoupons Exit】 AvailableDTOList: {}; ExpiredDTOList: {}",
                couponAvailableDTOList, couponExpiredDTOList);
        return  map;
    }
}
