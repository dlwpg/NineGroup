package com.group.utils;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
//不会被shiro拦截的监听器
public class A implements SessionListener {
    @Override
    public void onStart(Session session) {
        System.out.println("onStart");
    }

    @Override
    public void onStop(Session session) {
        System.out.println("onStop");
    }

    @Override
    public void onExpiration(Session session) {
        System.out.println("onExpiration");
    }
}
