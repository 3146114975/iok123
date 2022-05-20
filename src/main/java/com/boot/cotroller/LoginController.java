package com.boot.cotroller;

import com.boot.bean.User;
import com.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

import static com.boot.utils.Constants.user_session;

@Controller
public class LoginController {

    @Autowired
   UserService userService;

    @GetMapping(value = {"/","/login"})
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public String check(User user, HttpSession session, Model model){

        User loginuser = userService.getLoginInfo(user.getUserCode());

        if(loginuser != null){
            if(loginuser.getUserPassword().equals(user.getUserPassword())){
                session.setAttribute("loginUser",loginuser);
                //登录成功重定向到main.html;  重定向防止表单重复提交    redirext
                return "redirect:/frame.html";
            }else{
                model.addAttribute("error","账号或密码错误");
                return "login";
            }
        }else{
            model.addAttribute("error","账号或密码错误");
            return "login";
        }
    }
    @GetMapping("/frame.html")
    public String index(){
        return "frame";
    }

    //退出登录
    @GetMapping("/loginout")
    public String loginout(HttpSession session){
        //注销session，返回登录界面
        session.removeAttribute("loginUser");
        return "/login";
    }

}
