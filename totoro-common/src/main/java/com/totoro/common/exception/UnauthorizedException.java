package com.totoro.common.exception;

import com.totoro.common.response.ResultMessageEnum;

/**
 * 未认证异常
 *
 * @author lwyang  2020/2/27
 */
public class UnauthorizedException extends TotoroException {

    public UnauthorizedException() {
    }

    public UnauthorizedException(ResultMessageEnum resultMessage) {
        super(resultMessage);
    }
}
