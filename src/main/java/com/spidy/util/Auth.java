package com.spidy.util;
import jakarta.servlet.http.*;
public class Auth {
 public static boolean student(HttpServletRequest r){return r.getSession(false)!=null&&r.getSession(false).getAttribute("userId")!=null;}
 public static boolean admin(HttpServletRequest r){return r.getSession(false)!=null&&r.getSession(false).getAttribute("adminId")!=null;}
}
