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

    /**
     * 添加地址信息
     *
     * @param userId 用户id
     * @param addressDTO addressDTO
     * @return Optional
     */
    Optional insertAddress(Long userId, AddressDTO addressDTO);

    /**
     * 更新地址信息
     *
     * @param userId 用户id
     * @param addressesId 地址id
     * @param addressDTO addressDTO
     * @return Optional
     */

    Optional updateAddress(Long userId, Long addressesId, AddressDTO addressDTO);

    /**
     * 删除地址信息
     *
     * @param userId 用户id
     * @param addressesId 地址id
     * @return Optional
     */
    Optional deleteAddress(Long userId, Long addressesId);
}
