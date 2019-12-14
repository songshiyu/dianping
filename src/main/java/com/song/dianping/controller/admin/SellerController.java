package com.song.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.song.dianping.commom.*;
import com.song.dianping.model.SellerModel;
import com.song.dianping.request.PageQuery;
import com.song.dianping.request.SellerRequest;
import com.song.dianping.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    //用户列表
    @RequestMapping("/index")
    @Adminpermission
    public ModelAndView index(PageQuery pageQuery){
        //使用分页查询
        PageHelper.startPage(pageQuery.getPage(),pageQuery.getSize());
        List<SellerModel> sellerModelList = sellerService.selectAll();

        PageInfo<SellerModel> pageInfo = new PageInfo<>(sellerModelList);

        ModelAndView modelAndView = new ModelAndView("/admin/seller/index");

        modelAndView.addObject("data",pageInfo);
        modelAndView.addObject("CONTROLLER_NAME","seller");
        modelAndView.addObject("ACTION_NAME","index");

        return modelAndView;
    }

    @RequestMapping("/createpage")
    @Adminpermission
    public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView("/admin/seller/create");

        modelAndView.addObject("CONTROLLER_NAME","seller");
        modelAndView.addObject("ACTION_NAME","create");

        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @Adminpermission
    public String create(@Valid SellerRequest sellerRequest, BindingResult bindingResult) throws BussinessException {
        if (bindingResult.hasErrors()){
            throw new BussinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        SellerModel sellerModel = new SellerModel();
        sellerModel.setName(sellerRequest.getName());

        sellerService.creat(sellerModel);

        return "redirect:/admin/seller/index";
    }

    @RequestMapping(value = "/down",method = RequestMethod.POST)
    @Adminpermission
    @ResponseBody
    public CommomRes down(@RequestParam(value = "id")Integer id) throws BussinessException {
        SellerModel sellerModel = sellerService.changeStatus(id,1);
        return CommomRes.create(sellerModel);
    }

    @RequestMapping(value = "/up",method = RequestMethod.POST)
    @Adminpermission
    @ResponseBody
    public CommomRes up(@RequestParam(value = "id")Integer id) throws BussinessException {
        SellerModel sellerModel = sellerService.changeStatus(id,0);
        return CommomRes.create(sellerModel);
    }
}
