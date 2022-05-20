package com.boot.service.Impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.bean.User;

import com.boot.mapper.UserMapper;
import com.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{

    @Autowired
    UserMapper userMapper;

    @Override
    public User getLoginInfo(String userCode){

        return userMapper.getLoginInfo(userCode);
    }

    @Override
    public List<User> getUserList(String username, int userRole) {
        return userMapper.getUserList(username,userRole);
    }


}
