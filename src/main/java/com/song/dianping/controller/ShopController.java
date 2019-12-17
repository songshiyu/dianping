package com.song.dianping.controller;

import com.song.dianping.commom.BussinessException;
import com.song.dianping.commom.CommomRes;
import com.song.dianping.commom.EmBussinessError;
import com.song.dianping.model.ShopModel;
import com.song.dianping.service.CategoryService;
import com.song.dianping.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller("/shop")
@RequestMapping("shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private CategoryService categoryService;

    //推荐v1.0
    @RequestMapping("/recommend")
    @ResponseBody
    public CommomRes recommend(@RequestParam(name = "longitude")BigDecimal longitude,
                               @RequestParam(name = "latitude")BigDecimal latitude) throws BussinessException {

        if (longitude == null || latitude == null){
            throw new BussinessException(EmBussinessError.PARAMETER_VALIDATION_ERROR);
        }
        List<ShopModel> shopModelList = shopService.recommend(longitude, latitude);
        return CommomRes.create(shopModelList);
    }
}
