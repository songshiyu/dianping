package com.song.dianping.commom;

public class CommonError {

    //错误码
    private Integer errCode;

    private String errMsg;

    public CommonError(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public CommonError(EmBussinessError emBussinessError) {
        this.errCode = emBussinessError.getErrCode();
        this.errMsg = emBussinessError.getErrMsg();
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
