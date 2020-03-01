package com.totoro.common.advice;

import com.totoro.common.exception.*;
import com.totoro.common.response.Result;
import com.totoro.common.response.ResultMessageEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

/**
 * 全局异常处理
 *
 * @author lwyang  2020/2/27
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result validatedError(MethodArgumentNotValidException e){
        log.error("参数验证错误：{}", e.getBindingResult().getFieldError().getDefaultMessage());
        return Result.badRequest(ResultMessageEnum.PARAMETER_ERROR, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(SQLException.class)
    public Result sqlException(SQLException e){
        log.error("数据库错误：{}" ,e.getMessage());
        return Result.badRequest(ResultMessageEnum.SQL_OPERATION_ERROR, e.getMessage());
    }

    @ExceptionHandler(TotoroException.class)
    public Result badRequest(TotoroException e){
        return Result.badRequest(e.getResultMessageEnum());
    }

    @ExceptionHandler(NotFoundException.class)
    public Result notFound(NotFoundException e){
        return Result.notFound(e.getResultMessageEnum());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Result unauthorized(UnauthorizedException e){
        return Result.unauthorized(e.getResultMessageEnum());
    }

    @ExceptionHandler(ForbiddenException.class)
    public Result forbidden(ForbiddenException e){
        return Result.forbidden(e.getResultMessageEnum());
    }

    @ExceptionHandler(RequestTimeoutException.class)
    public Result requestTimeout(RequestTimeoutException e){
        return Result.requestTimeout(e.getResultMessageEnum());
    }

    @ExceptionHandler(TooManyRequestsException.class)
    public Result tooManyRequests(TooManyRequestsException e){
        return Result.tooManyRequests(e.getResultMessageEnum());
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public Result internalServerError(InternalServerErrorException e){
        return Result.internalServerError(e.getResultMessageEnum());
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public Result serviceUnavailable(ServiceUnavailableException e){
        return Result.serviceUnavailable(e.getResultMessageEnum());
    }

}
