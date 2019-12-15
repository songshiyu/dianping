package com.song.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.song.dianping.commom.Adminpermission;
import com.song.dianping.commom.BussinessException;
import com.song.dianping.commom.CommonUtil;
import com.song.dianping.commom.EmBussinessError;
import com.song.dianping.model.CategoryModel;
import com.song.dianping.model.ShopModel;
import com.song.dianping.request.CategoryCreateReq;
import com.song.dianping.request.PageQuery;
import com.song.dianping.request.ShopCreateReq;
import com.song.dianping.service.CategoryService;
import com.song.dianping.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller("/admin/shop")
@RequestMapping("/admin/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @RequestMapping("/index")
    @Adminpermission
    public ModelAndView index(PageQuery pageQuery){
        //使用分页查询
        PageHelper.startPage(pageQuery.getPage(),pageQuery.getSize());
        List<ShopModel> shopModelList = shopService.selectAll();

        PageInfo<ShopModel> pageInfo = new PageInfo<>(shopModelList);

        ModelAndView modelAndView = new ModelAndView("/admin/shop/index");

        modelAndView.addObject("data",pageInfo);
        modelAndView.addObject("CONTROLLER_NAME","shop");
        modelAndView.addObject("ACTION_NAME","index");

        return modelAndView;
    }

    @RequestMapping("/createpage")
    @Adminpermission
    public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView("/admin/shop/create");

        modelAndView.addObject("CONTROLLER_NAME","shop");
        modelAndView.addObject("ACTION_NAME","create");

        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @Adminpermission
    public String create(@Valid ShopCreateReq shopCreateReq, BindingResult bindingResult) throws BussinessException {
        if(bindingResult.hasErrors()){
            throw new BussinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        ShopModel shopModel = new ShopModel();
        shopModel.setIconUrl(shopCreateReq.getIconUrl());
        shopModel.setAddress(shopCreateReq.getAddress());
        shopModel.setCategoryId(shopCreateReq.getCategoryId());
        shopModel.setEndTime(shopCreateReq.getEndTime());
        shopModel.setStartTime(shopCreateReq.getStartTime());
        shopModel.setLongitude(shopCreateReq.getLongitude());
        shopModel.setLatitude(shopCreateReq.getLatitude());
        shopModel.setName(shopCreateReq.getName());
        shopModel.setPricePerMan(shopCreateReq.getPricePerMan());
        shopModel.setSellerId(shopCreateReq.getSellerId());

        shopService.create(shopModel);


        return "redirect:/admin/shop/index";
    }
}
