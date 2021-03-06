package com.group.service;

import com.group.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionEvent;

public interface UserService {
    String checkLogin(User user, String remember, HttpServletResponse response, HttpServletRequest request);

    User queryCookie(HttpServletRequest request);

    //统计男生人数
    User tongjinan();

    //统计总人数
    User tongjizoong();
}
