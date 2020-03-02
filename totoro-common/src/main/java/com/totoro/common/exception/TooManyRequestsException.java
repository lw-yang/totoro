package com.totoro.common.exception;

import com.totoro.common.response.ResultMessageEnum;

/**
 * 请求次数超范围异常
 *
 * @author lwyang  2020/2/27
 */
public class TooManyRequestsException extends TotoroException {

    public TooManyRequestsException() {
    }

    public TooManyRequestsException(ResultMessageEnum resultMessage) {
        super(resultMessage);
    }
}
