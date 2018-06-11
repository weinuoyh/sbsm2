package com.sxt.service.impl;

import com.sxt.mapper.UserMapper;
import com.sxt.pojo.User;
import com.sxt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shaxueting on 2017/6/5.
 */
@Service
public class UserServiceImpl implements UserService {
@Autowired
    private UserMapper userMapper;
    @Override
    public User getUserById(String id) {
//        UserExample example=new UserExample();
//        example.createCriteria().andIdEqualTo(id);

        User user=userMapper.selectByPrimaryKey(id);
        user.setAge(12);

        return user;
    }

    @Override
    public List selectByExample() {
        List<User> list = userMapper.selectList();
        System.out.println("111");
        return list;
    }
}
