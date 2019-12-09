package com.song.dianping.service.impl;

import com.song.dianping.commom.BussinessException;
import com.song.dianping.commom.EmBussinessError;
import com.song.dianping.dal.UserModelMapper;
import com.song.dianping.model.UserModel;
import com.song.dianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class UserServiceImpl  implements UserService{

    @Autowired
    private UserModelMapper userModelMapper;

    @Override
    public UserModel getUser(Integer id) {
        return userModelMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public UserModel register(UserModel regusterUser) throws BussinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        regusterUser.setPassword(EncoderByMd5(regusterUser.getPassword()));
        regusterUser.setCreateAt(new Date());
        regusterUser.setUpdateAt(new Date());

        try {
            userModelMapper.insertSelective(regusterUser);
        }catch (DuplicateKeyException ex){
            throw new BussinessException(EmBussinessError.REGISTER_DUP_FAIL);
        }
        return getUser(regusterUser.getId());
    }

    @Override
    public UserModel login(String telphone, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BussinessException {
        UserModel userModel = userModelMapper.selectByTelphoneAndPassword(telphone,EncoderByMd5(password));
        if (userModel == null){
            throw new BussinessException(EmBussinessError.LOGIN_FAIL);
        }
        return userModel;
    }

    //MD5加密
    private String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //首先确认MD5方式
        MessageDigest digest = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();

        return base64Encoder.encode(digest.digest(str.getBytes("UTF-8")));
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UserServiceImpl userService = new UserServiceImpl();

        String s = userService.EncoderByMd5("111");
        System.out.println(s);
    }
}
