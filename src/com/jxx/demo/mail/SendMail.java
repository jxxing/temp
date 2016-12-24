package com.jxx.demo.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {


    public boolean mailsend(String mail, String title, String content) {
        try {
            // 接受内容
            String to_mail = mail;
            String to_title = title;
            String to_content = content;
            
            //从sword配置文件取得
            String smtpAddr = "smtp.163.com";//"smtp.sina.com";//(String) ConfManager.getValueByKey("smtpAddr");
            String emailHost = "jxxing123@163.com";//"fantasyep_jay@sina.com";//(String) ConfManager.getValueByKey("emailHost");
            String emailUser = "jxxing123@163.com";//"fantasyep_jay@sina.com";//(String) ConfManager.getValueByKey("email-user");
            String emailPwd = "jxx8631204";//"123456789";//(String) ConfManager.getValueByKey("email-pwd");
                 
            // 建立邮件会话
            Properties props = new Properties();
            props.put("mail.smtp.host", smtpAddr);// 存储发送邮件服务器的信息
            props.put("mail.smtp.auth", "true");// 同时通过验证
            Session s = Session.getInstance(props);// 根据属性新建一个邮件会话
            s.setDebug(true);

            // 由邮件会话新建一个消息对象
            MimeMessage message = new MimeMessage(s);// 由邮件会话新建一个消息对象

            // 设置邮件
            InternetAddress from = new InternetAddress(emailHost);
            message.setFrom(from);// 设置发件人
            InternetAddress to = new InternetAddress(to_mail);
            message.setRecipient(Message.RecipientType.TO, to);// 设置收件人,并设置其接收类型为TO
            message.setSubject(to_title);// 设置主题
            message.setText(to_content);// 设置信件内容
            message.setSentDate(new Date());// 设置发信时间

            // 发送邮件
            message.saveChanges();// 存储邮件信息
            Transport transport = s.getTransport("smtp");
            // 以smtp方式登录邮箱,第一个参数是发送邮件用的邮件服务器SMTP地址,第二个参数为用户名,第三个参数为密码
            transport.connect(smtpAddr, emailUser, emailPwd);
            transport.sendMessage(message, message.getAllRecipients());// 发送邮件,其中第二个参数是所有已设好的收件人地址
            transport.close();
            return true;
        } catch (MessagingException e) {
            // out.println("发送失败!");
            return false;
        }
       
    }
    public static void main(String args[]){
    	SendMail s = new SendMail();
    	s.mailsend("983440432@qq.com", "我的邮件2", "hello baby你好！");
    }
}
