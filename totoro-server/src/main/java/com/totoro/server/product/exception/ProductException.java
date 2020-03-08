package com.totoro.server.product.exception;

import com.totoro.common.exception.TotoroException;
import com.totoro.common.response.ResultMessageEnum;

/**
 * 商品类别模块自定义异常
 *
 * @author lwyang  2020/3/8
 */

public class ProductException extends TotoroException {
    public ProductException() {
    }

    public ProductException(ResultMessageEnum resultMessage) {
        super(resultMessage);
    }
}
