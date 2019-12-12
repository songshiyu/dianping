package com.song.dianping.aspect;

import com.song.dianping.commom.Adminpermission;
import com.song.dianping.commom.CommomRes;
import com.song.dianping.commom.CommonError;
import com.song.dianping.commom.EmBussinessError;
import com.song.dianping.controller.admin.AdminController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Aspect
@Configuration  //声明为一个bean
public class ControllerAspect {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @Around("execution(* com.song.dianping.controller.admin.*.*(..)) " +
            "&& @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object adminControllerBeforeValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Adminpermission adminpermission = method.getAnnotation(Adminpermission.class);
        if (adminpermission == null) {
            //公众方法
            Object resultObject = joinPoint.proceed();
            return resultObject;
        }

        //判断当前管理员是否登录
        String email = (String) httpServletRequest.getSession().getAttribute(AdminController.CURRENT_ADMIN_SESSION);
        if (email == null) {
            if (adminpermission.produceType().equals("text/html")) {
                httpServletResponse.sendRedirect("/admin/admin/login");
                return null;
            } else {
                CommonError commonError= new CommonError(EmBussinessError.ADMIN_SHOULD_LOGIN);
                return CommomRes.create(commonError,"fail");
            }
        } else {
            Object resultObject = joinPoint.proceed();
            return resultObject;
        }
    }
}
