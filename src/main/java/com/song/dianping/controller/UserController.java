package com.song.dianping.controller;

import com.song.dianping.commom.*;
import com.song.dianping.model.UserModel;
import com.song.dianping.request.LoginReq;
import com.song.dianping.request.RegisterReq;
import com.song.dianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller("/user")
@RequestMapping("/user")
public class UserController {

    private static final String CURRENT_USER_SESSION = "current_user";

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping("/get")
    @ResponseBody
    public CommomRes getUser(@RequestParam(name = "id") Integer id) throws BussinessException {
        UserModel user = userService.getUser(id);
        if (user == null) {
            throw new BussinessException(EmBussinessError.NO_OBJECT_FOUND);
        } else {
            return CommomRes.create(user);
        }
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("/index.html");
        String userName = "宋时雨";
        modelAndView.addObject("name",userName);
        return modelAndView;
    }

    @RequestMapping("/register")
    @ResponseBody
    public CommomRes register(@Valid @RequestBody RegisterReq registerReq, BindingResult bindingResult) throws UnsupportedEncodingException, NoSuchAlgorithmException, BussinessException {
        if (bindingResult.hasErrors()){
            throw new BussinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }

        UserModel userModel = new UserModel();
        userModel.setTelphone(registerReq.getTelphone());
        userModel.setNickName(registerReq.getNickName());
        userModel.setPassword(registerReq.getPassword());
        userModel.setGender(registerReq.getGender());

        UserModel registerUser = userService.register(userModel);

        return CommomRes.create(registerUser);
    }

    @RequestMapping("/login")
    @ResponseBody
    public CommomRes login(@RequestBody @Valid LoginReq loginReq,BindingResult bindingResult) throws BussinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (bindingResult.hasErrors()){
           throw new BussinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,CommonUtil.processErrorString(bindingResult));
        }

        UserModel userModel = userService.login(loginReq.getTelphone(), loginReq.getPassword());

        httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION,userModel);

        return CommomRes.create(userModel);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public CommomRes logout(){
        httpServletRequest.getSession().invalidate();
        return CommomRes.create(null);
    }

    //获取当前用户信息
    @RequestMapping("/getCurrentUser")
    @ResponseBody
    public CommomRes getCurrentUser(){
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute(CURRENT_USER_SESSION);
        return CommomRes.create(userModel);
    }
}
