package com.song.dianping.controller;

import com.song.dianping.commom.BussinessException;
import com.song.dianping.commom.CommomRes;
import com.song.dianping.commom.CommonError;
import com.song.dianping.commom.EmBussinessError;
import com.song.dianping.model.UserModel;
import com.song.dianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller("/user")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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
}
