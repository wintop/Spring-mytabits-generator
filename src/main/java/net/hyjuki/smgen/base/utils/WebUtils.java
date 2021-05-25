package net.hyjuki.smgen.base.utils;

import net.hyjuki.smgen.model.UserAccount;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.util.DigestUtils;

public class WebUtils {
    public static UserAccount getUserInfo() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            return (UserAccount) subject.getPrincipal();
        }
        return null;
    }

    public static String md5Pwd(String password, String salt) {
        if (salt != null) {
            password = password + salt;
        }
        return DigestUtils.md5DigestAsHex((password+salt).getBytes());
    }

    public static UserAccount getWechatUser() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            return (UserAccount) subject.getPrincipal();
        }
        return null;
    }
}
