package com.boot.cotroller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.bean.Role;
import com.boot.bean.User;
import com.boot.service.RoleService;
import com.boot.service.UserService;
import com.boot.servlet.UserServlet;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserView {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping("/userview")
    public String userview(){

        return "user/userview";
    }


    @GetMapping("/userlist")
    public String userlist(HttpSession session,
                           Model model,
                           HttpServletRequest req,
                           @RequestParam(value = "pn",defaultValue = "1")Integer pn){

        String queryName = req.getParameter("queryName");
        String queryUserRole = req.getParameter("queryUserRole");

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("userName",queryName);
        if(queryUserRole != null && !queryUserRole.equals("") && !queryUserRole.equals("0")){
            Integer role = Integer.parseInt(queryUserRole);
            queryWrapper.eq("userRole", role);
        }

        List<User> userList = userService.list(queryWrapper);

        //把查询到的用户列表返回到前端
//        model.addAttribute("userList",userList);

  /*
       得到角色的列表
*/
        List<Role> roleList = roleService.list();
        model.addAttribute("roleList",roleList);


        //分页
        Page<User> userPage = new Page<>(pn, 5);
        Page<User> page = userService.page(userPage, null);

        model.addAttribute("page",page);
        return "user/userlist";
    }

    @GetMapping("/useradd")
    public String Useradd(){
        return "user/useradd";
    }

    @GetMapping("/userdelete/{id}")
    public String userdelete(@PathVariable("id") Integer userid,
                             @RequestParam(value = "pn",defaultValue = "1") Integer pn,
                             RedirectAttributes rs){

        boolean b = userService.removeById(userid);

        rs.addAttribute("pn",pn);

        return "redirect:/userlist";
    }

}
