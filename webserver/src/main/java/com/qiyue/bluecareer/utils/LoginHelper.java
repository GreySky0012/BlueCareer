package com.qiyue.bluecareer.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Qiyue on 2017/10/13
 */
public class LoginHelper {
    private static LoginHelper myInstance;
    private static Map userInfoMap = new ConcurrentHashMap<String, LogInUserInfo>();
    private LoginHelper() {

    }

    public static synchronized LoginHelper getInstance() {
        if (myInstance == null) {
            myInstance = new LoginHelper();
        }
        return myInstance;
    }

    public boolean isAlreadyLogin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return false;
        }
        for (Cookie cookie : cookies) {
            if ("uuid".equals(cookie.getName())) {
                return userInfoMap.get(cookie.getValue()) != null;
            }
        }
        return false;
    }

    /**
     * 保存登录的用户信息，并添加cookie信息
     * @param response
     * @param key
     * @param id
     */
    public void register(HttpServletResponse response, String key, int id, boolean isAdmin) {
        Cookie cookie = new Cookie("uuid", key);
        cookie.setSecure(true);
        cookie.setMaxAge(8 * 60 * 60);
        response.addCookie(cookie);
        LogInUserInfo userInfo = new LogInUserInfo(id, isAdmin);
        userInfoMap.put(key, userInfo);
    }

    class LogInUserInfo {
        boolean isAdmin;
        int id;

        public LogInUserInfo(int id, boolean isAdmin) {
            this.id = id;
            this.isAdmin = isAdmin;
        }

    }
}
