package com.totoro.common.exception;

import com.totoro.common.response.ResultMessage;

/**
 * 请求资源找不到异常
 *
 * @author lwyang  2020/2/27
 */
public class NotFoundException extends TotoroException {

    public NotFoundException() {
    }

    NotFoundException(ResultMessage resultMessage){
        super(resultMessage);
    }
}
