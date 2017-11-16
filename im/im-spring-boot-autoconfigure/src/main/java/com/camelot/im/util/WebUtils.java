package com.camelot.im.util;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {


  private static final String SESSION_USER_ID_KEY = "USER_ID";

  /**
   * . 从request中获取IP
   */
  public static String getRemoteHost(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
  }


  /**
   * . session中放置登录用户ID
   */
  public static void setUserIdToSession(HttpServletRequest request, String userId) {
    request.getSession().setAttribute(SESSION_USER_ID_KEY, userId);
  }

  /**
   * . 从session中去userId
   */
  public static String getUserIdFromSession(HttpServletRequest request) {
    Object obj = request.getSession().getAttribute(SESSION_USER_ID_KEY);
    return obj == null ? "1" : (String) obj;
  }

}
