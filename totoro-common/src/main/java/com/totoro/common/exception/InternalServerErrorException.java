package com.totoro.common.exception;

import com.totoro.common.response.ResultMessageEnum;

/**
 * 服务内部错误异常
 *
 * @author lwyang  2020/2/27
 */
public class InternalServerErrorException extends TotoroException {

    public InternalServerErrorException() {
    }

    public InternalServerErrorException(ResultMessageEnum resultMessage) {
        super(resultMessage);
    }
}
