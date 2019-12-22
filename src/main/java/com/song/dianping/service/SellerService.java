package com.song.dianping.service;

import com.song.dianping.commom.BussinessException;
import com.song.dianping.model.SellerModel;

import java.util.List;

public interface SellerService {

    SellerModel creat(SellerModel sellerModel);

    SellerModel get(Integer id);

    List<SellerModel> selectAll();

    SellerModel changeStatus(Integer id,Integer disableFlag) throws BussinessException;

    Integer countAllSeller();
}
