package com.totoro.common.utils;

/**
 * 常用正则表达式
 *
 * @author lwyang  2020/2/29
 */
public interface RegExpConstant {

    String PHONE = "(13|14|15|17|18|19)[0-9]{9}";

    String EMAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";


}
