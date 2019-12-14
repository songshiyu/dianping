package com.song.dianping.controller.admin;

import com.song.dianping.commom.Adminpermission;
import com.song.dianping.model.SellerModel;
import com.song.dianping.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    //用户列表
    @RequestMapping("/index")
    @Adminpermission
    public ModelAndView index(){
        List<SellerModel> sellerModelList = sellerService.selectAll();

        ModelAndView modelAndView = new ModelAndView("/admin/seller/index");

        modelAndView.addObject("data",sellerModelList);
        modelAndView.addObject("CONTROLLER_NAME","seller");
        modelAndView.addObject("ACTION_NAME","index");

        return modelAndView;
    }
}
