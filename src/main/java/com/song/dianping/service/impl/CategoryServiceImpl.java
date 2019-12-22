package com.song.dianping.service.impl;

import com.song.dianping.commom.BussinessException;
import com.song.dianping.commom.EmBussinessError;
import com.song.dianping.dal.CategoryModelMapper;
import com.song.dianping.model.CategoryModel;
import com.song.dianping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryModelMapper categoryModelMapper;

    @Override
    @Transactional
    public CategoryModel creat(CategoryModel categoryModel) throws BussinessException {
        categoryModel.setCreatedAt(new Date());
        categoryModel.setUpdatedAt(new Date());

        try {
            categoryModelMapper.insertSelective(categoryModel);
        }catch (DuplicateKeyException ex){
            throw new BussinessException(EmBussinessError.CATEGORY_NAME_DUPLICATED);
        }

        return get(categoryModel.getId());
    }

    @Override
    public CategoryModel get(Integer id) {
        return categoryModelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CategoryModel> selectAll() {
        return categoryModelMapper.selectAll();
    }

    @Override
    public Integer countAllCategory() {
        return categoryModelMapper.countAllCategory();
    }
}
