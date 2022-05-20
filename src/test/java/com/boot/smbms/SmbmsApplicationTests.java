package com.boot.smbms;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.bean.Role;
import com.boot.bean.User;
import com.boot.service.RoleService;
import com.boot.service.UserService;
import com.mysql.cj.x.protobuf.MysqlxExpr;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@Slf4j
@SpringBootTest
class SmbmsApplicationTests {

    @Autowired
    UserService userService;
    @Test
    void contextLoads() {

        //模糊查询  查询包含孙字 和 角色为3 的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("userName","孙")
                .eq("userRole",3);

        List<User> list = userService.list(queryWrapper);

        log.info("查询的用户：｛｝"+list);
    }
    @Test
    void select(){

        List<User> userList = userService.getUserList("%孙%", 0);

        log.info("查询的用户：｛｝"+userList);
    }
    @Autowired
    RoleService roleService;
    @Test
    void selectRolelist(){

        List<Role> list = roleService.list();
        log.info("角色列表：｛｝"+list);
    }
    @Test
    void insert(){
        User user = new User();
        user.setId(123);
        user.setUserCode("123");
        user.setUserName("iok");
        user.setUserPassword("123");
        boolean save = userService.save(user);
        log.info("是否成功插入：{}"+save);
    }
    @Test
    void delete(){
        QueryWrapper<User> deleteuser = new QueryWrapper<>();
        deleteuser.eq("id",123);
        boolean remove = userService.remove(deleteuser);
        log.info("是否删除成功：{}"+remove);
    }
    @Test
    void update(){

    }

}
