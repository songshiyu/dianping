package com.song.dianping.controller;

import com.song.dianping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

}
