package com.totoro.common.response;

/**
 * 定义出错时返回的错误码及错误信息
 *
 * @author lwyang  2020/2/27
 */

public enum ResultMessage {

    /**
     * 错误码及错误信息
     */
    ERROR_MESSAGE(-1,"-1"),
    ;

    private int errCode;
    private String errMessage;

    ResultMessage(int errCode, String errMessage) {
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    public int getErrCode() {
        return errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }
}
