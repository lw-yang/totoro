package com.totoro.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

/**
 * 封装统一返回Restful
 *
 * @author lwyang  2020/2/27
 * @see org.springframework.http.ResponseEntity
 */

public class Result<T> extends ResponseEntity {

    public Result(HttpStatus status) {
        super(status);
    }

    /**
     * GET,PUT,PATCH方法成功返回使用
     *
     * @return Result
     */
    public static Result success() {
        return (Result) ResponseEntity.ok().build();
    }

    /**
     * @param body    返回数据
     * @param <T>数据类型
     * @return Result
     */
    public static <T> Result<T> success(T body) {
        return (Result<T>) ResponseEntity.ok(body);
    }

    /**
     * POST方法成功返回使用
     *
     * @param body 返回数据
     * @param <T>  数据类型
     * @return Result
     */
    public static <T> Result<T> created(T body) {
        return (Result<T>) ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    /**
     * DELETE方法成功返回使用
     *
     * @return Result
     */
    public static Result emptyContent() {
        return (Result) ResponseEntity.noContent().build();
    }


    /**
     * 客户端请求出错时使用 BAD_REQUEST
     *
     * @param resultMessage 出错详细信息
     * @return Result
     */
    public static Result badRequest(ResultMessage resultMessage) {
        return (Result) ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Result.errPut(resultMessage));
    }

    /**
     * 客户端请求出错时使用 UNAUTHORIZED
     *
     * @param resultMessage 出错详细信息
     * @return Result
     */
    public static Result unauthorized(ResultMessage resultMessage) {
        return (Result) ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Result.errPut(resultMessage));
    }

    /**
     * 客户端请求出错时使用 FORBIDDEN
     *
     * @param resultMessage 出错详细信息
     * @return Result
     */
    public static Result forbidden(ResultMessage resultMessage){
        return (Result) ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Result.errPut(resultMessage));
    }

    /**
     * 客户端请求出错时使用 NOT_FOUND
     *
     * @param resultMessage 出错详细信息
     * @return Result
     */
    public static Result notFound(ResultMessage resultMessage){
        return (Result) ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Result.errPut(resultMessage));
    }

    /**
     * 客户端请求出错时使用 REQUEST_TIMEOUT
     *
     * @param resultMessage 出错详细信息
     * @return Result
     */
    public static Result requestTimeout(ResultMessage resultMessage){
        return (Result) ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                .body(Result.errPut(resultMessage));
    }

    /**
     * 客户端请求出错时使用 TOO_MANY_REQUESTS
     *
     * @param resultMessage 出错详细信息
     * @return Result
     */
    public static Result tooManyRequests(ResultMessage resultMessage){
        return (Result) ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(Result.errPut(resultMessage));
    }

    /**
     * 服务器端自身出错时使用 INTERNAL_SERVER_ERROR
     *
     * @param resultMessage 出错详细信息
     * @return Result
     */
    public static Result internalServerError(ResultMessage resultMessage){
        return (Result) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Result.errPut(resultMessage));
    }

    /**
     * 服务器端不可用时使用 SERVICE_UNAVAILABLE
     *
     * @param resultMessage 出错详细信息
     * @return Result
     */
    public static Result serviceUnavailable(ResultMessage resultMessage){
        return (Result) ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Result.errPut(resultMessage));
    }

    /**
     * 将错误消息封装成HashMap返回
     *
     * @param errorMessage 错误消息
     * @return HashMap
     */
    private static HashMap errPut(ResultMessage errorMessage) {
        HashMap<String, Object> result = new HashMap<>(2, 1);
        result.put(ResultConstant.ERROR_CODE, errorMessage.getErrCode());
        result.put(ResultConstant.ERROR_MESSAGE, errorMessage.getErrMessage());
        return result;
    }

}
