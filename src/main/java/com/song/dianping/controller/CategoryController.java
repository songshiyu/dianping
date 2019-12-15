package com.song.dianping.controller;

import com.song.dianping.commom.CommomRes;
import com.song.dianping.model.CategoryModel;
import com.song.dianping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/category")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ResponseBody
    @RequestMapping("/list")
    public CommomRes list(){
        List<CategoryModel> categoryModelList = categoryService.selectAll();
        return CommomRes.create(categoryModelList);

    }
}
