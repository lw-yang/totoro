package com.totoro.common.exception;

import com.totoro.common.response.ResultMessage;

/**
 * 自定义统一异常
 *
 * @author lwyang  2020/2/27
 */
public class TotoroException extends RuntimeException {

    private ResultMessage resultMessage;

    public TotoroException(){
        super();
    }

    public TotoroException(ResultMessage resultMessage){
        this.resultMessage = resultMessage;
    }

    public ResultMessage getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(ResultMessage resultMessage) {
        this.resultMessage = resultMessage;
    }
}
