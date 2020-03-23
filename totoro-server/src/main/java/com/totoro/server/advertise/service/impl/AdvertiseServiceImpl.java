package com.totoro.server.advertise.service.impl;

import com.totoro.common.response.ResultMessageEnum;
import com.totoro.db.dao.DailyChoiceMapper;
import com.totoro.db.dao.DailySpecialMapper;
import com.totoro.db.dao.ProductMapper;
import com.totoro.db.entity.DailyChoice;
import com.totoro.db.entity.DailySpecial;
import com.totoro.db.entity.Product;
import com.totoro.server.advertise.dto.DailyChoiceDTO;
import com.totoro.server.advertise.dto.DailySpecialDTO;
import com.totoro.server.advertise.exception.AdvertiseException;
import com.totoro.server.advertise.service.AdvertiseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * AdvertiseService
 *
 * @author lwyang  2020/3/21
 */

@Service
@Slf4j
public class AdvertiseServiceImpl implements AdvertiseService {

    @Autowired
    DailySpecialMapper dailySpecialMapper;

    @Autowired
    DailyChoiceMapper dailyChoiceMapper;

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<DailySpecialDTO> listDailySpecial(){
        List<DailySpecial> dailySpecialList = dailySpecialMapper.selectByEffectiveDate();
        List<DailySpecialDTO> dailySpecialDTOList = new ArrayList<>(dailySpecialList.size());
        dailySpecialList.forEach(i -> {
            DailySpecialDTO dailySpecialDTO = new DailySpecialDTO();
            BeanUtils.copyProperties(i, dailySpecialDTO);

            Product product = productMapper.selectByPrimaryKey(i.getProductId());
            if (Objects.isNull(product)){
                log.error("【listDailySpecial】商品查询失败");
                throw new AdvertiseException(ResultMessageEnum.PRODUCT_SELECT_FAILURE);
            }

            dailySpecialDTO.setProductThumb(product.getThumb());
            dailySpecialDTOList.add(dailySpecialDTO);
        });
        return dailySpecialDTOList;
    }

    @Override
    public List<DailyChoiceDTO> listDailyChoice(){
        List<DailyChoice> dailyChoiceList = dailyChoiceMapper.selectAll();
        List<DailyChoiceDTO> dailyChoiceDTOList = new ArrayList<>(dailyChoiceList.size());
        dailyChoiceList.forEach(i -> {
            DailyChoiceDTO dailyChoiceDTO = new DailyChoiceDTO();
            BeanUtils.copyProperties(i, dailyChoiceDTO);

            Product product = productMapper.selectByPrimaryKey(i.getProductId());
            if (Objects.isNull(product)){
                log.error("【listDailySpecial】商品查询失败");
                throw new AdvertiseException(ResultMessageEnum.PRODUCT_SELECT_FAILURE);
            }

            dailyChoiceDTO.setProductName(product.getName());
            dailyChoiceDTO.setProductPrice(product.getPrice().setScale(2));
            dailyChoiceDTO.setProductDesc(product.getDescription());
            dailyChoiceDTOList.add(dailyChoiceDTO);
        });
        return dailyChoiceDTOList;
    }
}
