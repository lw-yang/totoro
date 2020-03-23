package com.totoro.server.advertise.service;

import com.totoro.server.advertise.dto.DailyChoiceDTO;
import com.totoro.server.advertise.dto.DailySpecialDTO;

import java.util.List;

/**
 * @author lwyang  2020/3/21
 */
public interface AdvertiseService {

    /**
     * 查询所有今日特价商品
     *
     * @return List<DailySpecialDTO>
     */
    List<DailySpecialDTO> listDailySpecial();

    List<DailyChoiceDTO> listDailyChoice();
}
