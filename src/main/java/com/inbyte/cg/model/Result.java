package com.inbyte.cg.model;

import java.io.Serializable;

/**
 * 
 *
 * @author chenjw
 * @date 202076
 */
public class Result<E> implements Serializable {

    /**
     * ,ResultStatus
     *
     * @mock 200
     */
    private Integer status;
    /**
     * 
     *
     * @mock 
     */
    private String msg;
    /**
     * 
     */
    private E data;

    public Result(Integer status, String msg, E data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Result() {
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 
     */
    public boolean succeeded() {
        return status == ResultStatus.Success.code;
    }

    /**
     * 
     */
    public boolean failed() {
        return status != ResultStatus.Success.code;
    }

    /**
     * 
     *
     * @param msg , :  String ,  success(String msg, T data) ,  msg 
     */
    public static <T> Result<T> success(String msg) {
        return success(msg, null);
    }

    /**
     * 
     *  200
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> success() {
        return success(null, null);
    }

    /**
     * 
     *
     * @param data , :  String ,  success(String msg, T data) ,  msg 
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return success(null, data);
    }

    /**
     * 
     *
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(String msg, T data) {
        return new Result(ResultStatus.Success.code, msg, data);
    }

    /**
     * 
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> failure(String msg) {
        return failure(msg, null);
    }

    /**
     * 
     *
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> failure(String msg, T data) {
        return new Result(ResultStatus.Failure.code, msg, data);
    }

    public static <T> Result<T> failure(ResultStatus statusCode) {
        return new Result(statusCode.code, statusCode.name, null);
    }

    /**
     * 
     *  500
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String msg) {
        return new Result(ResultStatus.Internal_Server_Error.code, msg, null);
    }

    public static <T> Result<T> set(ResultStatus status) {
        return set(status, status.name, null);
    }

    public static <T> Result<T> set(ResultStatus status, String msg) {
        return set(status, msg, null);
    }

    public static <T> Result<T> set(ResultStatus status, String msg, T data) {
        return new Result(status.code, msg, data);
    }

    public static <T> Result<T> valueOf(Result resp) {
        return new Result(resp.getStatus(), resp.getMsg(), null);
    }


}
