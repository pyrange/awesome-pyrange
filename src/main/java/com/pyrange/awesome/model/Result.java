package com.pyrange.awesome.model;

import java.io.Serializable;

/**
 * 实体包装类
 *
 * @author chenjw
 * @date 2020年7月6日
 */
public class Result<E> implements Serializable {

    /**
     * 请求响应状态,参考字典ResultStatus
     *
     * @mock 200
     */
    private Integer status;
    /**
     * 消息提示
     *
     * @mock 请求成功
     */
    private String msg;
    /**
     * 返回结果
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
     * 请求结果成功
     */
    public boolean succeeded() {
        return status == ResultStatus.Success.code;
    }

    /**
     * 请求结果失败
     */
    public boolean failed() {
        return status != ResultStatus.Success.code;
    }

    /**
     * 业务处理成功
     *
     * @param msg 处理成功提示消息, 注意: 当响应结果为 String 类型时, 需要调用 success(String msg, T data) 重载方法, 否则会将结果设置为 msg 消息
     */
    public static <T> Result<T> success(String msg) {
        return success(msg, null);
    }

    /**
     * 业务处理成功
     * 状态码 200
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> success() {
        return success(null, null);
    }

    /**
     * 业务处理成功
     *
     * @param data 响应数据, 注意: 当响应结果为 String 类型时, 需要调用 success(String msg, T data) 重载方法, 否则会将结果设置为 msg 消息
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return success(null, data);
    }

    /**
     * 业务处理成功
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
     * 业务处理失败
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> failure(String msg) {
        return failure(msg, null);
    }

    /**
     * 业务处理失败
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
     * 系统异常
     * 状态码 500
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
