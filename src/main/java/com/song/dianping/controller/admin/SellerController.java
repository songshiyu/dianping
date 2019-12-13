package com.song.dianping.controller.admin;

import com.song.dianping.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/admin")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    //用户列表
}
