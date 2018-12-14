package com.blog.cloud;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Tesjjj {

    private static final String FROM = "550283387@qq.com";


    public static void main(String[] args) {
//        sendEmail1();
        sendEmail2();
    }

    private static void sendEmail1 () {
        try{
            HtmlEmail email=new HtmlEmail();//创建电子邮件对象
            email.setDebug(true);
            email.setHostName("SMTP.qq.com");//设置发送电子邮件使用的服务器主机名
            email.setSmtpPort(587);//设置发送电子邮件使用的邮件服务器的TCP端口地址
            email.setAuthenticator(new DefaultAuthenticator(FROM, "hhemvoiirzdzbfih"));//邮件服务器身份验证
            email.setFrom(FROM);//设置发信人邮箱
            email.setSubject("小白利用代码发送的测试邮件");//设置邮件主题
            email.setMsg("这是小白发送的测试邮件，胖子，不要害怕");//设置邮件文本内容
            email.setContent("这是小白发送的测试邮件，胖子，不要害怕", "text/html;charset=utf-8");
            email.addTo("1390241959@qq.com");//设置收件人
            email.send();//发送邮件
        }catch(EmailException e){
            e.printStackTrace();
        }
    }

    private static void sendEmail2 () {
        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.qq.com");
            props.put("mail.smtp.port", 465);
            props.put("mail.smtp.auth", true);
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.timeout", 10000);


            // 验证账号及密码，密码需要是第三方授权码
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM, "hhemvoiirzdzbfih");
                }
            };
            Session session = Session.getInstance(props, auth);
            // 2.创建一个Message，它相当于是邮件内容
            Message message = new MimeMessage(session);
            // 设置发送者
            message.setFrom(new InternetAddress(FROM));
            // 设置发送方式与接收者
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("2030238228@qq.com"));
            // 设置主题
            message.setSubject("这是一封测试邮箱提醒");
            // 设置内容
            message.setContent("<a target='_BLANK' href='http://www.baidu.com'>先生/女士您好，请点击此链接激活账号</a>", "text/html;charset=utf-8");
            // 3.创建 Transport用于将邮件发送
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
