package com.pyrange.awesome.model;

/**
 * 接口响应状态
 *
 * @author chenjw
 * @date 2016/10/20
 */
public enum ResultStatus {

    // 业务处理成功, 付款与退款结果表示处理完成
    Success(200, "业务处理成功"),
    // 提示message消息内容
    Failure(400, "业务处理失败"),
    // 用户身份验证不通过, 拉起登录窗口
    Internal_Server_Error(500, "服务器内部异常"),

    ;

    public final int code;
    public final String name;

    ResultStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
