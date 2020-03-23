package com.totoro.common.advice;

import com.totoro.common.exception.TotoroException;
import com.totoro.common.response.Result;
import com.totoro.common.response.ResultMessageEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * 捕获程序异常
 *
 * <b>NOTE:</b> 使 {@code SQLException} 优先于 {@code Exception} 捕获
 *
 * @author lwyang  2020/3/1
 */

@RestControllerAdvice
@Slf4j
@Order(-1)
public class TotoroExceptionHandler {


    @ExceptionHandler(TotoroException.class)
    public Result badRequest(TotoroException e){
        return Result.badRequest(e.getResultMessageEnum());
    }

    @ExceptionHandler(SQLException.class)
    public Result sqlException(SQLException e){
        log.error("【数据库错误】：{}" ,e.getMessage());
        e.printStackTrace();
        return Result.badRequest(ResultMessageEnum.SQL_OPERATION_ERROR);
    }

}
