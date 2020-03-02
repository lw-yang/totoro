package com.totoro.common.exception;

import com.totoro.common.response.ResultMessageEnum;

/**
 * 自定义统一异常
 *
 * @author lwyang  2020/2/27
 */
public class TotoroException extends RuntimeException {

    private ResultMessageEnum resultMessage;

    public TotoroException(){
        super();
    }

    public TotoroException(ResultMessageEnum resultMessage){
        this.resultMessage = resultMessage;
    }

    public ResultMessageEnum getResultMessageEnum() {
        return resultMessage;
    }

    public void setResultMessageEnum(ResultMessageEnum resultMessage) {
        this.resultMessage = resultMessage;
    }
}
