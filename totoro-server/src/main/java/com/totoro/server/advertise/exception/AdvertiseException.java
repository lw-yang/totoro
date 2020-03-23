package com.totoro.server.advertise.exception;

import com.totoro.common.exception.TotoroException;
import com.totoro.common.response.ResultMessageEnum;

/**
 * 广告模块自定义异常
 *
 * @author lwyang  2020/3/21
 */
public class AdvertiseException extends TotoroException {
    public AdvertiseException() {
    }

    public AdvertiseException(ResultMessageEnum resultMessage) {
        super(resultMessage);
    }
}
