package com.song.dianping.service;

import com.song.dianping.commom.BussinessException;
import com.song.dianping.model.ShopModel;

import java.math.BigDecimal;
import java.util.List;

public interface ShopService {

    ShopModel create(ShopModel shopModel) throws BussinessException;

    ShopModel get(Integer id);

    List<ShopModel> selectAll();

    Integer countAllShop();

    List<ShopModel> recommend(BigDecimal longtitude,BigDecimal latitude);
}
