package com.thinkgem.jeesite.modules.bx.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/** 
  * 
  * 用作服务器邮箱登录校验 
  */
public class MailAuthenticator extends Authenticator {
    //登录邮箱的用户名
    private String username;
    //密码
    private String password;

    public MailAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }


 @Override
 protected PasswordAuthentication getPasswordAuthentication() {

 return new PasswordAuthentication(username,password);
}
    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

}