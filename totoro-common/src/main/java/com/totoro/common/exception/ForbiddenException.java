package com.totoro.common.exception;

import com.totoro.common.response.ResultMessageEnum;

/**
 * 无权限异常
 *
 * @author lwyang  2020/2/27
 */
public class ForbiddenException extends TotoroException {

    public ForbiddenException() {
    }

    public ForbiddenException(ResultMessageEnum resultMessage) {
        super(resultMessage);
    }
}
