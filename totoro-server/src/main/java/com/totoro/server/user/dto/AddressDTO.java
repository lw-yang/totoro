package com.totoro.server.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lwyang  2020/3/6
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {

    private Long id;

    private String province;

    private String city;

    private String county;

    private String detail;

    private String receiver;

    private String receiverPhone;

    private Long postalCode;

    private Byte defaultAddress;

    private String address;
}
