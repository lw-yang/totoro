package com.totoro.server.user.service.impl;

import com.totoro.common.response.ResultMessageEnum;
import com.totoro.db.dao.AddressMapper;
import com.totoro.db.entity.Address;
import com.totoro.server.user.dto.AddressDTO;
import com.totoro.server.user.exception.UserException;
import com.totoro.server.user.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author lwyang  2020/3/6
 */

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressMapper addressMapper;

    @Override
    public List<AddressDTO> listAddresses(Long userId) {
        log.info("【listAddresses Enter】userId: {}", userId);

        List<Address> addressList = addressMapper.selectByUserId(userId);
        List<AddressDTO> addressDTOList = new ArrayList<>(addressList.size());

        addressList.forEach(i -> {
            AddressDTO addressDTO = new AddressDTO();
            BeanUtils.copyProperties(i, addressDTO);
            String address = i.getProvince() + i.getCity() + i.getCounty() + i.getDetail();
            addressDTO.setAddress(address);
            addressDTOList.add(addressDTO);
        });

        log.info("【listAddresses Exit】addressList: {}", addressDTOList);
        return addressDTOList;
    }

    @Override
    public Optional insertAddress(Long userId, AddressDTO addressDTO){
        log.info("【insertAddress Enter】userId: {}; addressDTO: {}", userId, addressDTO);

        Address address = new Address();
        BeanUtils.copyProperties(addressDTO, address);
        address.setUserId(userId);
        address.setCreateTime(LocalDateTime.now());

        if (addressDTO.getDefaultAddress() == 1){
            int updateResult = addressMapper.updateNotDefaultByPrimaryKey(userId);
            if (updateResult < 1){
                log.error("【insertAddress】地址设为非默认失败");
                throw new UserException(ResultMessageEnum.ADDRESS_UPDATE_NOT_DEFAULT_FAILURE);
            }
        }

        int insertResult = addressMapper.insertSelective(address);
        if (insertResult != 1){
            log.error("【insertAddress】地址添加失败");
            throw new UserException(ResultMessageEnum.ADDRESS_INSERT_FAILURE);
        }

        log.info("【insertAddress Exit】");
        return Optional.empty();
    }

    @Override
    public Optional updateAddress(Long userId, Long addressesId, AddressDTO addressDTO){
        log.info("【editAddress Enter】");

        Address address = new Address();
        BeanUtils.copyProperties(addressDTO, address);
        address.setId(addressesId);
        address.setUpdateTime(LocalDateTime.now());

        if (addressDTO.getDefaultAddress() == 1){
            int updateResult = addressMapper.updateNotDefaultByPrimaryKey(userId);
            if (updateResult < 1){
                log.error("【insertAddress】地址设为非默认失败");
                throw new UserException(ResultMessageEnum.ADDRESS_UPDATE_NOT_DEFAULT_FAILURE);
            }
        }

        Address addressInfo = addressMapper.selectByPrimaryKey(addressesId);
        if (Objects.isNull(addressInfo)){
            log.error("【editAddress】地址不存在");
            throw new UserException(ResultMessageEnum.ADDRESS_NOT_EXIST);
        }

        if (!addressInfo.getUserId().equals(userId)){
            log.error("【editAddress】地址修改失败，用户Id不匹配");
            throw new UserException(ResultMessageEnum.ADDRESS_UPDATE_USER_ID_NOT_MATCH);
        }

        int updateResult = addressMapper.updateByPrimaryKeySelective(address);
        if (updateResult != 1){
            log.error("【editAddress】地址修改失败");
            throw new UserException(ResultMessageEnum.ADDRESS_UPDATE_FAILURE);
        }

        log.info("【editAddress Exit】");
        return Optional.empty();
    }

    @Override
    public Optional deleteAddress(Long userId, Long addressesId){
        log.info("【deleteAddress】");

        Address addressInfo = addressMapper.selectByPrimaryKey(addressesId);
        if (Objects.isNull(addressInfo)){
            log.error("【deleteAddress】地址不存在");
            throw new UserException(ResultMessageEnum.ADDRESS_NOT_EXIST);
        }

        if (!addressInfo.getUserId().equals(userId)){
            log.error("【deleteAddress】地址删除失败，用户Id不匹配");
            throw new UserException(ResultMessageEnum.ADDRESS_DELETE_USER_ID_NOT_MATCH);
        }

        int deleteResult = addressMapper.deleteByPrimaryKey(addressesId);
        if (deleteResult != 1){
            log.error("【deleteAddress】地址删除失败");
            throw new UserException(ResultMessageEnum.ADDRESS_DELETE_FAILURE);
        }

        return Optional.empty();
    }
}
