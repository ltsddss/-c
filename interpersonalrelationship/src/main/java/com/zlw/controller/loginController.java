package com.zlw.controller;

import com.zlw.pojo.person;
import com.zlw.server.PersonServerImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class loginController {

    @Autowired
    private PersonServerImpl personServer;

    @RequestMapping("/toLogin")
    public String tologin(){
        return "views/login";
    }

    @RequestMapping("/Login")
    public String login(HttpSession session, String username, String password, Model model){
        Subject subject= SecurityUtils.getSubject();

        //封装用户登录数据
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);

        try{
            subject.login(token);//执行登录操作，如果没有异常就不会被捕获

            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户不存在");
            return "views/login";
        }
        catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "views/login";
        }
    }

    @RequestMapping("/toRegister")
    public String toretiger(){
        return "views/Register";
    }

    @RequestMapping("/Register")
    public String register(String username,String password,Model model){
        person p =personServer.queryUser(username);

        if(p!=null){
            model.addAttribute("msg","用户名已存在");
            return "views/Register";
        }
        else {
            person per=new person();

            per.setName(username);

            per.setPassword(password);

            personServer.addUser(per);

            return "views/login";
        }
    }

}
