//////////////////发送邮件的主类

package com.thinkgem.jeesite.modules.bx.util;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.List;
import java.util.Properties;

/** 
  * 发送邮件的主类
  */
public class SendMail {
     //发送邮件的props文件
    private final transient Properties props=System.getProperties();
    //邮件服务器登录验证
    private transient MailAuthenticator authenticator;
    //邮箱session
    private transient Session session;
    /**
     * 初始化发送邮件
     * @param smtpHostName邮件服务器地址
     * @param username发送邮件的用户名
     * @param password密码
     */
    public SendMail(final String smtpHostName,final String username,final String password){
        init(username,password,smtpHostName);
    }
    /**
     * 初始化发送邮件
     * @param username 发送邮件的用户名
     * @param password 密码
     */
    public SendMail(final String username,final String password){
//通过邮箱地址解析出smtp服务器，对大多数邮箱都管用
        final String smtpHostName="smtp."+username.split("@")[1];
        init(username,password,smtpHostName);
    }
    /**
     * 
     * 初始化
     * @param username 发送邮件的用户名
     * @param password 密码
     * @param smtpHostName SMTP主机地址
     */
    private void init(String username, String password, String smtpHostName) {
//初始化props
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", smtpHostName);
//验证
        authenticator=new MailAuthenticator(username,password);
//创建session
        session= Session.getInstance(props,authenticator);
    }
    /**
     * 发送邮件
     * @param recipient收件人邮箱地址
     * @param subject邮件主题
     * @param content邮件内容
     * @throws MessagingException  
     * @throws AddressException  
     */
    public void send(String recipient,String subject,Object content) throws AddressException, MessagingException {
//创建mime类型的邮件
        final MimeMessage message=new MimeMessage(session);
//设置发信人
        message.setFrom(new InternetAddress(authenticator.getUsername()));
//设置收件人
        message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
//设置主题
        message.setSubject(subject);
//设置邮件内容
        message.setContent(content.toString(), "text/html;charset=utf-8");
//发送
        Transport.send(message,message.getRecipients(RecipientType.TO));
    }
    /**
     * 群发邮件
     * @param recipients 收件人们
     * @param subject 主题
     * @param content 内容
     * @throws AddressException
     * @throws MessagingException
     */
    public void send(List<String> recipients,String subject,Object content) throws AddressException, MessagingException {
//创建mime类型的邮件
        final MimeMessage message=new MimeMessage(session);
//设置发件人
        message.setFrom(new InternetAddress(authenticator.getUsername()));
//设置收件人们
        InternetAddress[] addresses=new InternetAddress[recipients.size()];
        for(int i=0;i<recipients.size();i++){
            addresses[i]=new InternetAddress(recipients.get(i));
        }
        message.setRecipients(RecipientType.TO,addresses);
//设置主题
        message.setSubject(subject);
//设置邮件内容
        message.setContent(content.toString(), "text/html;charset=utf-8");
//发送
        Transport.send(message);
    }


}
