package com.group.controller;

import com.group.pojo.User;
import com.group.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService us;
  @RequestMapping("login.do")
  public  String login(){
      return "login";
  }
    @RequestMapping("checkLogin.do")
    @ResponseBody
    public String checkLogin(User user, String remember, HttpServletResponse response, HttpServletRequest request){
        String info = us.checkLogin(user,remember,response,request);
        return info;
    }
//    @RequestMapping("online.ajax")
//    @ResponseBody
//    public String selectOnline(HttpSession session) {
//
//        return "";
//
//    }
    //DefaultFilter //这个类里面定义所有shiro所有的过滤器
    @RequestMapping("success.do")
    public String success(){

        return "index";
    }
    @RequestMapping("de.do")
    public String de(){

        return "book/addbook";
    }
    //退出用户
    @RequestMapping(value = "logout.do",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public User logout(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        User p = us.queryCookie(request);
        System.err.println(p);
        return p;
    }


    @RequestMapping("logouts.do")
    public String logouts(HttpServletRequest request){
        System.out.println("xxxxx");
            ServletContext context = request.getSession().getServletContext();
            int online=(Integer) context.getAttribute("onLineNum")-1;
            context.setAttribute("onLineNum",online);
            Subject subject=SecurityUtils.getSubject();
            subject.logout();
            return "redirect:user/login.do";
    }


    @RequestMapping("showWelcome.do")
    public String showWelcome(){
        //要获取服务器某些并通过EL表达式显示在welcome.jsp
        return "welcome";
    }

    @RequestMapping("/getServerTime.ajax")
    @ResponseBody
    public String getServerTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String  time= df.format(new Date());
        return time;
    }

}
