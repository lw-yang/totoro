package com.totoro.server.user.enums;

/**
 * 用户注册类型
 *
 * @author lwyang  2020/2/29
 */
public enum RegisterTypeEnum {

    /**
     *
     */
    EMAIL("EMAIL"),
    PHONE("PHONE"),
    USERNAME("USERNAME"),
    ;

    private String type;

    RegisterTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
