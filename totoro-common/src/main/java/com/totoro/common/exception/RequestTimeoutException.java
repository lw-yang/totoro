package com.totoro.common.exception;

import com.totoro.common.response.ResultMessage;

/**
 * 请求超时异常
 *
 * @author lwyang  2020/2/27
 */
public class RequestTimeoutException extends TotoroException{

    public RequestTimeoutException() {
    }

    public RequestTimeoutException(ResultMessage resultMessage) {
        super(resultMessage);
    }
}
