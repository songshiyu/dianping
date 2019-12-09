package com.song.dianping.service;

import com.song.dianping.commom.BussinessException;
import com.song.dianping.model.UserModel;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface UserService {
    UserModel getUser(Integer id);

    UserModel register(UserModel regusterUser) throws BussinessException, UnsupportedEncodingException, NoSuchAlgorithmException;

    UserModel login(String telphone,String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BussinessException;
}
