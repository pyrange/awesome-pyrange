package com.inbyte.cg.model;

/**
 * 
 *
 * @author chenjw
 * @date 2016/10/20
 */
public enum ResultStatus {

    // , 
    Success(200, "success"),
    // message
    Failure(400, "failure"),
    // , 
    Internal_Server_Error(500, "internal server error"),

    ;

    public final int code;
    public final String name;

    ResultStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
