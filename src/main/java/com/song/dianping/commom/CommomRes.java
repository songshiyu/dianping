package com.song.dianping.commom;



public class CommomRes {

    //表明对应请求的返回处理结果，success或者fail
    private String status;

    //若status=success时，表明对应的返回json类数据。
    //若status=failed时，则data内将通用的错误码对应的格式
    private Object data;

    //定义一个通用的创建返回对象的方法
    public static CommomRes create(Object result){
        return CommomRes.create(result,"success");
    }

    public static CommomRes create(Object result,String status){
        CommomRes commomRes = new CommomRes();
        commomRes.setStatus(status);
        commomRes.setData(result);

        return commomRes;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
