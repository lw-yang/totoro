package com.totoro.common.exception;

import com.totoro.common.response.ResultMessage;

/**
 * 服务内部错误异常
 *
 * @author lwyang  2020/2/27
 */
public class InternalServerErrorException extends TotoroException {

    public InternalServerErrorException() {
    }

    public InternalServerErrorException(ResultMessage resultMessage) {
        super(resultMessage);
    }
}
