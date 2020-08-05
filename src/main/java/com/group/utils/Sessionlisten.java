package com.group.utils;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
//没有加shiro可以获取到session
//获取同时在线人数

public class Sessionlisten implements HttpSessionListener {
    private static int count = 0;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println(111);
        //通过event获得servletContext对象
        ServletContext application = httpSessionEvent.getSession().getServletContext();
        //拿到servletContext空间中储存的onLineNum
        Integer num = (Integer) application.getAttribute("onLineNum");
        if (num != null) {
            //如果存在，则将拿到的数量加一并到重新放入到servletContext空间中
            num = num + 1;
            application.setAttribute("onLineNum", num);
        } else {
            //如果不存在，则设置onLineNum为1
            application.setAttribute("onLineNum", 1);
        }
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("一个session关闭了");
        //通过event获得servletContext对象
        ServletContext application = event.getSession().getServletContext();
        //拿到servletContext空间中储存的onLineNum
        Integer num = (Integer) application.getAttribute("onLineNum");
        num = num - 1;
        //将拿到的数量减一并到重新放入到servletContext空间中
        application.setAttribute("onLineNum", num);
    }
}
