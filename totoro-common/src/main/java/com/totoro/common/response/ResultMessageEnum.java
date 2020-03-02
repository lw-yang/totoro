package com.totoro.common.response;

/**
 * 定义出错时返回的错误码及错误信息
 *
 * @author lwyang  2020/2/27
 */

public enum ResultMessageEnum {

    /**
     * 错误码及错误信息
     */
    ERROR_MESSAGE(-1, "出错啦,请稍后再试"),
    PARAMETER_ERROR(-2,"请求参数错误"),
    SQL_OPERATION_ERROR(-3, "数据库操作错误"),

    /**
     * 用户错误码 1xxx
     */
    USER_PASSWORD_ERROR(1000, "用户密码错误"),
    USER_NOT_EXIST(1002, "用户不存在"),
    USER_REGISTER_FAILURE(1003, "用户注册失败"),
    USER_UPDATE_FAILURE(1004, "用户更新失败"),
    USER_DELETE_FAILURE(1005,"用户删除失败"),
    USER_PASSWORD_NOT_SAME(1006, "用户注册两次密码不一致"),
    USER_EMAIL_EXIST(1007, "邮箱已存在"),
    USER_PHONE_EXIST(1008, "手机号已存在"),
    USER_NAME_EXIST(1009, "用户名已存在"),
    USER_TOKEN_EXPIRE(1010, "用token已失效"),

    ;

    private int errCode;
    private String errMessage;

    ResultMessageEnum(int errCode, String errMessage) {
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
