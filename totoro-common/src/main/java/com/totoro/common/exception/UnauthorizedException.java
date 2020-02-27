package com.totoro.common.exception;

import com.totoro.common.response.ResultMessage;

/**
 * 未认证异常
 *
 * @author lwyang  2020/2/27
 */
public class UnauthorizedException extends TotoroException {

    public UnauthorizedException() {
    }

    public UnauthorizedException(ResultMessage resultMessage) {
        super(resultMessage);
    }
}
