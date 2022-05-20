package com.boot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.bean.User;

import java.util.List;


public interface UserService extends IService<User>{

    public User getLoginInfo(String userCode);

    public List<User> getUserList(String username, int userRole);

}
