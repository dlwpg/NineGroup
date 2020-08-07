package com.group.utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail {
    //邮件服务器主机地址
//  private static String HOST="localhost";
    private static String HOST="smtp.qq.com";
    //帐号
//  private static String ACCOUNT = "zzzgdx@zgd.com";
    private static String ACCOUNT = "2389038599@qq.com";
    //密码(邮件密钥)
//  private static String PASSWORD = "123";
    private static String PASSWORD = "bvaklxlipxyddijd";



    /**
     * @param toUser  发送邮件给谁
     * @param title   邮件的标题
     * @param emailMsg  邮件信息
     */
    public static void sendMail(String toUser,String title, String emailMsg)throws AddressException, MessagingException {
        // 1.创建一个程序与邮件服务器会话对象 Session
        Properties props = new Properties();
        //设置发送的协议
        props.setProperty("mail.transport.protocol", "SMTP");

        //设置发送邮件的服务器
        props.setProperty("mail.host", HOST);
        props.setProperty("mail.smtp.auth", "true");// 指定验证为true

        // 创建验证器
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //设置发送人的帐号和密码      帐号          授权码
                return new PasswordAuthentication(ACCOUNT, PASSWORD);
            }
        };
        //会话
        Session session = Session.getInstance(props, auth);

        // 2.创建一个Message，它相当于是邮件内容
        Message message = new MimeMessage(session);

        //设置发送者
        message.setFrom(new InternetAddress(ACCOUNT));

        //设置发送方式与接收者
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toUser));

        //设置邮件主题
        message.setSubject(title);

        //设置邮件内容
        message.setContent(emailMsg, "text/html;charset=utf-8");

        // 3.创建 Transport用于将邮件发送
        Transport.send(message);
    }


//    public static void main(String[] args) throws AddressException, MessagingException {
//        String content = "<a href='http://localhost:8080/travel/activeUserServlet?code="+ user.getCode() +"'>请点击连接进行激活</a>";
//
//        SendEmail.sendMail("1017298177@qq.com", "祝福邮件2", "这是一封激活邮件，请<a href='#'>点击</a>");
//    }

}
