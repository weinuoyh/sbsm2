package com.sxt.service;

import com.sxt.pojo.User;

import java.util.List;

/**
 * Created by shaxueting on 2017/6/5.
 */
public interface UserService {
    User getUserById(String id);


    List selectByExample();
}
