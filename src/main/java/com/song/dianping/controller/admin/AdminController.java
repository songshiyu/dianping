package com.song.dianping.controller.admin;

import com.song.dianping.commom.Adminpermission;
import com.song.dianping.commom.BussinessException;
import com.song.dianping.commom.CommonUtil;
import com.song.dianping.commom.EmBussinessError;
import com.song.dianping.service.UserService;
import com.song.dianping.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/admin/admin")
public class AdminController {

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.encryptPassword}")
    private String adminPassword;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserService userService;

    public static final String CURRENT_ADMIN_SESSION = "currentAdminSession";

    @RequestMapping("/index")
    @Adminpermission
    public ModelAndView toIndex(){
        ModelAndView modelAndView = new ModelAndView("/admin/admin/index");
        modelAndView.addObject("userCount",userService.countAllUser());
        modelAndView.addObject("CONTROLLER_NAME","admin");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView toLogin(){
        return new ModelAndView("/admin/admin/login");
    }

    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public String doLogin(@RequestParam(name = "email")String email,
                                @RequestParam(name = "password")String password) throws BussinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)){
            throw new BussinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,"用户名或密码不能为空");
        }

        if (email.equals(adminEmail) && CommonUtils.EncodeByMD5(password).equals(adminPassword)){
            httpServletRequest.getSession().setAttribute(CURRENT_ADMIN_SESSION,email);
            return "redirect:/admin/admin/index";
        }else {
            //登录失败，抛出异常
            throw new BussinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,"用户名或密码不正确");
        }
    }


}
