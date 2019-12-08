package com.song.dianping.commom;


import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 * */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommomRes doError(HttpServletRequest request, HttpServletResponse response,Exception ex){
        if (ex instanceof BussinessException){
            return CommomRes.create(((BussinessException) ex).getCommonError(),"fail");
        }else if (ex instanceof NoHandlerFoundException){ //404 请求未找到
            CommonError commonError = new CommonError(EmBussinessError.NO_HANDLE_FOUND);
            return CommomRes.create(commonError,"fail");
        }else if (ex instanceof ServletRequestBindingException){ //请求参数错误异常
            CommonError commonError = new CommonError(EmBussinessError.BIND_EXCEPTION_ERROR);
            return CommomRes.create(commonError,"fail");
        }else {
            CommonError commonError = new CommonError(EmBussinessError.UNKNOWN_ERROR);
            return CommomRes.create(commonError,"fail");
        }
    }
}
