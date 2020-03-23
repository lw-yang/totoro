package com.totoro.server.user.controller;

import com.totoro.common.interceptor.NeedAuthentication;
import com.totoro.common.response.Result;
import com.totoro.server.user.dto.AddressDTO;
import com.totoro.server.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *  地址 Controller
 *
 * @author lwyang  2020/3/6
 */

@RestController
@RequestMapping("/users")
@NeedAuthentication
public class AddressController {

    @Autowired
    AddressService addressService;

    /**
     * 获取用户地址列表
     *
     * @param id 用户 Id
     * @return Result [status: 200]
     */
    @GetMapping("/{id}/addresses")
    public Result<List<AddressDTO>> listAddresses(@PathVariable Long id){
        return Result.success(addressService.listAddresses(id));
    }

    /**
     * 添加地址
     *
     * @param id 用户 id
     * @param addressDTO addressDTO
     * @return Result [status: 201]
     */
    @PostMapping("/{id}/addresses")
    public Result<Optional> insertAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO){
        return Result.created(addressService.insertAddress(id, addressDTO));
    }

    /**
     * 修改地址
     *
     * @param userId 用户 id
     * @param addressesId 地址 id
     * @param addressDTO  addressDTO
     * @return Result [status: 200]
     */
    @PutMapping("/{userId}/addresses/{addressesId}")
    public Result<Optional> updateAddress(@PathVariable Long userId,
                                        @PathVariable Long addressesId,
                                        @RequestBody AddressDTO addressDTO ){

        return Result.success(addressService.updateAddress(userId,addressesId, addressDTO));
    }

    /**
     * 删除地址
     *
     * @param userId 用户 id
     * @param addressesId 地址 id
     * @return  Result [status: 204]
     */
    @DeleteMapping("/{userId}/addresses/{addressesId}")
    public Result deleteAddress(@PathVariable Long userId, @PathVariable Long addressesId){
        addressService.deleteAddress(userId, addressesId);
        return Result.emptyContent();
    }
}
