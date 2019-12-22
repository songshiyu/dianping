package com.song.dianping.service;

import com.song.dianping.commom.BussinessException;
import com.song.dianping.model.CategoryModel;

import java.util.List;

public interface CategoryService {

    CategoryModel creat(CategoryModel categoryModel) throws BussinessException;

    CategoryModel get(Integer id);

    List<CategoryModel> selectAll();

    Integer countAllCategory();
}
