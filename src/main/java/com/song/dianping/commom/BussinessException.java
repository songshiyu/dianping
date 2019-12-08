package com.song.dianping.commom;

public class BussinessException extends Exception {

    private CommonError commonError;

    public BussinessException(EmBussinessError emBussinessError){
        super();
        this.commonError = new CommonError(emBussinessError);
    }

    public void setCommonError(CommonError commonError) {
        this.commonError = commonError;
    }

    public CommonError getCommonError() {

        return commonError;
    }
}
