package com.pyrange.awesome.model;

/**
 *
 * @author chenjw
 * @date 2016/10/20
 */
public enum ResultStatus {

    Success(200, "业务处理成功"),
    Failure(400, "业务处理失败"),
    Internal_Server_Error(500, "error"),

    ;

    public final int code;
    public final String name;

    ResultStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
