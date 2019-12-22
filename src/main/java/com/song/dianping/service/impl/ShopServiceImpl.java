package com.song.dianping.service.impl;

import com.song.dianping.commom.BussinessException;
import com.song.dianping.commom.EmBussinessError;
import com.song.dianping.dal.ShopModelMapper;
import com.song.dianping.model.CategoryModel;
import com.song.dianping.model.SellerModel;
import com.song.dianping.model.ShopModel;
import com.song.dianping.service.CategoryService;
import com.song.dianping.service.SellerService;
import com.song.dianping.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopModelMapper shopModelMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SellerService sellerService;

    @Override
    public ShopModel create(ShopModel shopModel) throws BussinessException {
        shopModel.setCreatedAt(new Date());
        shopModel.setUpdatedAt(new Date());

        //验证商家是否存在
        SellerModel sellerModel = sellerService.get(shopModel.getSellerId());
        if (sellerModel == null){
            throw new BussinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,"商户不存在");
        }

        //验证商家是否被禁用
        if (sellerModel.getDisabledFlag().intValue() == 1){
            throw new BussinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,"商户已禁用");
        }

        //验证类目是否存在
        CategoryModel categoryModel = categoryService.get(shopModel.getCategoryId());
        if(categoryModel == null){
            throw new BussinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR,"类目不存在");
        }
        shopModelMapper.insertSelective(shopModel);
        return get(shopModel.getId());

    }

    @Override
    public ShopModel get(Integer id) {
        ShopModel shopModel = shopModelMapper.selectByPrimaryKey(id);
        if(shopModel == null){
            return null;
        }
        shopModel.setSellerModel(sellerService.get(shopModel.getSellerId()));
        shopModel.setCategoryModel(categoryService.get(shopModel.getCategoryId()));
        return shopModel;
    }

    @Override
    public List<ShopModel> selectAll() {
        List<ShopModel> shopModelList = shopModelMapper.selectAll();
        shopModelList.forEach(shopModel -> {
            shopModel.setSellerModel(sellerService.get(shopModel.getSellerId()));
            shopModel.setCategoryModel(categoryService.get(shopModel.getCategoryId()));
        });
        return shopModelList;
    }

    @Override
    public Integer countAllShop() {
        return shopModelMapper.countAllShop();
    }

    @Override
    public List<ShopModel> recommend(BigDecimal longtitude, BigDecimal latitude) {

        return shopModelMapper.recommend(longtitude,latitude);
    }
}
