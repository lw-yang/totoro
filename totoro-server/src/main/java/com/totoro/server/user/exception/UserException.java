package com.totoro.server.user.exception;

import com.totoro.common.exception.TotoroException;
import com.totoro.common.response.ResultMessageEnum;

/**
 * User模块自定义异常
 *
 * @author lwyang  2020/2/28
 */
public class UserException extends TotoroException {
    public UserException() {
    }

    public UserException(ResultMessageEnum resultMessage) {
        super(resultMessage);
    }
}
