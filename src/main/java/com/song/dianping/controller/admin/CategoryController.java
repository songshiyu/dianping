package com.song.dianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.song.dianping.commom.*;
import com.song.dianping.model.CategoryModel;
import com.song.dianping.request.CategoryCreateReq;
import com.song.dianping.request.PageQuery;
import com.song.dianping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller("/admin/category")
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/index")
    @Adminpermission
    public ModelAndView index(PageQuery pageQuery){
        //使用分页查询
        PageHelper.startPage(pageQuery.getPage(),pageQuery.getSize());
        List<CategoryModel> CategoryModelList = categoryService.selectAll();

        PageInfo<CategoryModel> pageInfo = new PageInfo<>(CategoryModelList);

        ModelAndView modelAndView = new ModelAndView("/admin/category/index");

        modelAndView.addObject("data",pageInfo);
        modelAndView.addObject("CONTROLLER_NAME","category");
        modelAndView.addObject("ACTION_NAME","index");

        return modelAndView;
    }

    @RequestMapping("/createpage")
    @Adminpermission
    public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView("/admin/category/create");

        modelAndView.addObject("CONTROLLER_NAME","category");
        modelAndView.addObject("ACTION_NAME","create");

        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @Adminpermission
    public String create(@Valid CategoryCreateReq categoryCreateReq, BindingResult bindingResult) throws BussinessException {
        if (bindingResult.hasErrors()){
            throw new BussinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName(categoryCreateReq.getName());
        categoryCreateReq.setIconUrl(categoryCreateReq.getIconUrl());
        categoryCreateReq.setSort(categoryCreateReq.getSort());


        categoryService.creat(categoryModel);

        return "redirect:/admin/category/index";
    }
}
