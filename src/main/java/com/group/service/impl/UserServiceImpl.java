package com.group.service.impl;

import com.group.dao.UserMapper;
import com.group.pojo.User;
import com.group.service.UserService;
import com.group.utils.GetIp;
import com.group.utils.Sessionlisten;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionEvent;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper pm;
    @Autowired
    private GetIp getIp;
    @Autowired
    private UserMapper userMapper;

    @Override
    public String checkLogin(User p, String remember, HttpServletResponse response, HttpServletRequest request) {
        //1.拿到当前用户
        Subject s = SecurityUtils.getSubject();

        //2.判断当前用户是否被认证，并做相关处理
        if (!s.isAuthenticated()) {
            //UsernamePasswordToken 令牌类  稍后会把保存在里面账号密码和shiro的身份和凭证比对
            UsernamePasswordToken upt = new UsernamePasswordToken(p.getUsername(), p.getPassword());
            upt.setRememberMe(true);
            try {
                //进行认证(因为我们写了自定义的realm类，所以会自动到realm类里面去认证)
                s.login(upt);

                //登录成功
                //保存用户名和首页资源在session
                User user = new User();
                user = pm.queryPersonByPersonName(p.getUsername());
                System.err.println(user);
                s.getSession().setAttribute("userinfo", user);
                Set<String> roles = new HashSet<String>();
                //查询数据库得到用户的权限
                String role = pm.queryPersonByPersonName(p.getUsername()).getRole();
                roles.add(role);
                s.getSession().setAttribute("roles", roles);

                //获取首页信息
                HashMap<String, Object> indexinfo = new HashMap<String, Object>();
                //获取ip
                Object ip = getIp.getLocalHostLANAddress();
                //获取当前时间
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = simpleDateFormat.format(new Date());
                //获取账户
                String username = p.getUsername();
                //姓名
                String name = "";
                if (pm.queryPersonByPersonName(p.getUsername()).getSex().equals("男")) {
                    name = "Mr." + p.getUsername();
                } else {
                    name = "Miss." + p.getUsername();
                }
                //获取服务器名称
                String servicename = "D:/tomcat/apache-tomcat-8.0.32";
                //获取服务器ip
                String serviceip = request.getSession().getServletContext().getRealPath("/");
                //获取在线人数
                // 获取 ServletContext 对象
                ServletContext context = request.getSession().getServletContext();
                int online=(Integer) context.getAttribute("onLineNum")+1;
                context.setAttribute("onLineNum",online);

                indexinfo.put("ip", ip);
                indexinfo.put("date", date);
                indexinfo.put("username", username);
                indexinfo.put("name", name);
                indexinfo.put("servicename", servicename);
                indexinfo.put("serviceip", serviceip);

                s.getSession().setAttribute("indexinfo", indexinfo);


                //判断复选框的状态
                if (remember.equals("YES")) {
                    //1.创建cookie  Servlet  cookie的使用
                   /* Cookie c = new Cookie("USERNAME",p.getPersonName());
                    Cookie c2 = new Cookie("PASSWORD",p.getPersonPassword());
                    //2.设置cookie的时间
                    c.setMaxAge(30*24*60*60);
                    c2.setMaxAge(30*24*60*60);
                    //3.将cookie回写给浏览器
                    response.addCookie(c);
                    response.addCookie(c2);*/

                    //在shiro  cookie的使用
                    //1.创建SimpleCookie
                    SimpleCookie simpleCookie_username = new SimpleCookie();
                    SimpleCookie simpleCookie_password = new SimpleCookie();
                    //2,在cookie存值
                    simpleCookie_username.setName("USERNAME");
                    simpleCookie_username.setValue(p.getUsername());

                    simpleCookie_password.setName("PASSWORD");
                    simpleCookie_password.setValue(p.getPassword() + p.getUsername());
                    //3.设置cookie时候
                    simpleCookie_username.setMaxAge(30 * 24 * 60 * 60);
                    simpleCookie_password.setMaxAge(30 * 24 * 60 * 60);

                    //4.回写给浏览器
                    simpleCookie_username.saveTo(request, response);
                    simpleCookie_password.saveTo(request, response);

                } else {
                    Cookie[] cookies = request.getCookies();
                    //System.out.println(cookies);
                    if (cookies != null) {
                        for (Cookie c : cookies) {
                            if (c.getName().equals("USERNAME")) {
                                //System.out.println(222);
                                //servlet 如何删除Cookie ,将时间设置为0 并返回给浏览器
                                /*c.setMaxAge(0);
                                response.addCookie(c);*/
                                SimpleCookie sc = new SimpleCookie();
                                sc.setName("USERNAME");
                                sc.setValue("");
                                sc.setMaxAge(0);
                                sc.saveTo(request, response);
                            }
                            if (c.getName().equals("PASSWORD")) {
                                SimpleCookie sc = new SimpleCookie();
                                sc.setName("PASSWORD");
                                sc.setValue("");
                                sc.setMaxAge(0);
                                sc.saveTo(request, response);
                            }
                        }
                    }
                }
                return "success";
            } catch (AuthenticationException e) {
                //登录失败
                return "ERROR";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //查询是否有所需要的cookie
    @Override
    public User queryCookie(HttpServletRequest request) {

        User p = new User();
        p.setUsername("");
        p.setPassword("");
        //得到所有的cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("USERNAME")) {
                    p.setUsername(c.getValue());
                }
                if (c.getName().equals("PASSWORD")) {
                    p.setPassword(c.getValue());
                }

            }
            p.setPassword(p.getPassword().replace(p.getUsername(), ""));
            return p;

        }

        return p;
    }

    @Override
    public User tongjinan() {
        return userMapper.tongjinan();
    }

    @Override
    public User tongjizoong() {
        return userMapper.tongjizong();
    }

}
