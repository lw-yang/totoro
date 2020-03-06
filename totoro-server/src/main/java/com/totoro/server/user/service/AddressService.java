package com.totoro.server.user.service;

import com.totoro.server.user.dto.AddressDTO;

import java.util.List;
import java.util.Optional;

/**
 * @author lwyang  2020/3/6
 */
public interface AddressService {

    /**
     * 查询用户所有地址
     *
     * @param userId userId
     * @return List<AddressDTO>
     */
    List<AddressDTO> listAddresses(Long userId);

    Optional insertAddress(Long userId, AddressDTO addressDTO);

    Optional updateAddress(Long userId, Long addressesId, AddressDTO addressDTO);

    Optional deleteAddress(Long userId, Long addressesId);
}
