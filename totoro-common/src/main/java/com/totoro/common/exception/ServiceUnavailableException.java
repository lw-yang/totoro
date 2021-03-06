package com.totoro.common.exception;

import com.totoro.common.response.ResultMessageEnum;

/**
 * 服务不可用异常
 *
 * @author lwyang  2020/2/27
 */
public class ServiceUnavailableException extends TotoroException {
    public ServiceUnavailableException() {
    }

    public ServiceUnavailableException(ResultMessageEnum resultMessage) {
        super(resultMessage);
    }
}
