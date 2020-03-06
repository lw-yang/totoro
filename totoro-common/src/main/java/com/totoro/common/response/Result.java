package com.totoro.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Objects;

/**
 * 封装统一返回Restful
 *
 * @author lwyang  2020/2/27
 * @see org.springframework.http.ResponseEntity
 */

public class Result<T> extends ResponseEntity<T> {

    private Result(HttpStatus status) {
        super(status);
    }

    private Result(T body, HttpStatus status) {
        super(body, status);
    }

    /**
     * GET,PUT,PATCH方法成功返回使用
     *
     * @return Result
     */
    public static Result success() {
        return new Result(HttpStatus.OK);
    }

    /**
     * GET,PUT,PATCH方法成功返回使用,并返回相关数据
     *
     * @param <T>数据类型
     * @param body    返回数据
     * @return Result
     */
    public static <T> Result<T> success(T body) {
        return new Result<>(body, HttpStatus.OK);
    }

    /**
     * POST方法成功返回使用
     *
     * @param body 返回数据
     * @param <T>  数据类型
     * @return Result
     */
    public static <T> Result<T> created(T body) {
        return new Result<>(body, HttpStatus.CREATED);
    }

    /**
     * DELETE方法成功返回使用
     *
     * @return Result
     */
    public static Result emptyContent() {
        return new Result(HttpStatus.NO_CONTENT);
    }


    /**
     * 客户端请求出错时使用 BAD_REQUEST
     *
     * @param resultMessage 出错详细信息
     * @return Result
     */
    public static Result badRequest(ResultMessageEnum resultMessage) {
        return new Result<>(errPut(resultMessage), HttpStatus.BAD_REQUEST);
    }

    /**
     * 请求参数错误出错时使用 BAD_REQUEST
     *
     * @param resultMessage 出错详细信息
     * @return Result
     */
    public static Result badRequest(ResultMessageEnum resultMessage, String detail) {
        return new Result<>(errPut(resultMessage, detail), HttpStatus.BAD_REQUEST);
    }

    /**
     * 客户端请求出错时使用 UNAUTHORIZED
     *
     * @param resultMessage 出错详细信息
     * @return Result
     */
    public static Result unauthorized(ResultMessageEnum resultMessage) {
        return new Result<>(errPut(resultMessage), HttpStatus.UNAUTHORIZED);
    }

    /**
     * 客户端请求出错时使用 FORBIDDEN
     *
     * @param resultMessage 出错详细信息
     * @return Result
     */
    public static Result forbidden(ResultMessageEnum resultMessage){
        return new Result<>(errPut(resultMessage), HttpStatus.FORBIDDEN);
    }

    /**
     * 客户端请求出错时使用 NOT_FOUND
     *
     * @param resultMessage 出错详细信息
     * @return Result
     */
    public static Result notFound(ResultMessageEnum resultMessage){
        return new Result<>(errPut(resultMessage), HttpStatus.NOT_FOUND);
    }

    /**
     * 客户端请求出错时使用 REQUEST_TIMEOUT
     *
     * @param resultMessage 出错详细信息
     * @return Result
     */
    public static Result requestTimeout(ResultMessageEnum resultMessage){
        return new Result<>(errPut(resultMessage), HttpStatus.REQUEST_TIMEOUT);
    }

    /**
     * 客户端请求出错时使用 TOO_MANY_REQUESTS
     *
     * @param resultMessage 出错详细信息
     * @return Result
     */
    public static Result tooManyRequests(ResultMessageEnum resultMessage){
        return new Result<>(errPut(resultMessage), HttpStatus.TOO_MANY_REQUESTS);
    }

    /**
     * 服务器端自身出错时使用 INTERNAL_SERVER_ERROR
     *
     * @param resultMessage 出错详细信息
     * @return Result
     */
    public static Result internalServerError(ResultMessageEnum resultMessage){
        return new Result<>(errPut(resultMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 服务器端不可用时使用 SERVICE_UNAVAILABLE
     *
     * @param resultMessage 出错详细信息
     * @return Result
     */
    public static Result serviceUnavailable(ResultMessageEnum resultMessage){
        return new Result<>(errPut(resultMessage), HttpStatus.SERVICE_UNAVAILABLE);
    }

    private static HashMap errPut(ResultMessageEnum errorMessage) {
        return errPut(errorMessage, null);
    }
    /**
     * 将错误消息封装成HashMap返回
     *
     * @param errorMessage 错误消息
     * @return HashMap
     */
    private static HashMap errPut(ResultMessageEnum errorMessage, String detail) {
        HashMap<String, Object> result = new HashMap<>(2, 1);
        result.put(ResultConstant.ERROR_CODE, errorMessage.getErrCode());
        if (Objects.nonNull(detail)){
            result.put(ResultConstant.ERROR_MESSAGE, errorMessage.getErrMessage() + " => " + detail);
        }else {
            result.put(ResultConstant.ERROR_MESSAGE, errorMessage.getErrMessage());
        }

        return result;
    }

}
