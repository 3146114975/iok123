package com.boot.servlet;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.bean.User;
import com.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {

    @Autowired
    UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String queryName = req.getParameter("queryName");
        String queryUserRole = req.getParameter("queryUserRole");

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("userName",queryName);
        if(queryUserRole != null && !queryUserRole.equals("")){
            queryWrapper.eq("userRole", Integer.parseInt(queryUserRole));
        }

        List<User> userList = userService.list(queryWrapper);

        //把查询到的用户列表返回到前端
        req.setAttribute("userList",userList);

        req.getRequestDispatcher("userlist").forward(req,resp);
    }
}
