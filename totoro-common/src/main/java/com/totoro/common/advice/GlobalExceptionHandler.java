package com.totoro.common.advice;

import com.totoro.common.exception.*;
import com.totoro.common.response.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理
 *
 * @author lwyang  2020/2/27
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TotoroException.class)
    public Result badRequest(TotoroException e){
        return Result.badRequest(e.getResultMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public Result notFound(NotFoundException e){
        return Result.notFound(e.getResultMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Result unauthorized(UnauthorizedException e){
        return Result.unauthorized(e.getResultMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    public Result forbidden(ForbiddenException e){
        return Result.forbidden(e.getResultMessage());
    }

    @ExceptionHandler(RequestTimeoutException.class)
    public Result requestTimeout(RequestTimeoutException e){
        return Result.requestTimeout(e.getResultMessage());
    }

    @ExceptionHandler(TooManyRequestsException.class)
    public Result tooManyRequests(TooManyRequestsException e){
        return Result.tooManyRequests(e.getResultMessage());
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public Result internalServerError(InternalServerErrorException e){
        return Result.internalServerError(e.getResultMessage());
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public Result serviceUnavailable(ServiceUnavailableException e){
        return Result.serviceUnavailable(e.getResultMessage());
    }

}
