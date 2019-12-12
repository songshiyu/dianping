package com.song.dianping.commom;

public enum EmBussinessError {

    //通用的错误类型100000开头
    NO_OBJECT_FOUND(10001,"请求对象不存在"),
    NO_HANDLE_FOUND(10003,"找不到執行的路徑操作"),
    BIND_EXCEPTION_ERROR(10004,"请求参数错误"),
    UNKNOWN_ERROR(10002,"未知错误"),
    PARAMETER_VALIDATION_ERROR(10005,"请求参数检验失败"),

    //用户类型的错误
    REGISTER_DUP_FAIL(20001,"注册失败，用户已存在"),
    LOGIN_FAIL(200012,"登录失败，用户不存在"),

    //admin相关错误
    ADMIN_SHOULD_LOGIN(30001,"管理员需要先登录"),;

    private Integer errCode;

    private String errMsg;

    EmBussinessError(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
