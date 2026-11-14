package com.spidy.servlet;
import com.spidy.util.*;import jakarta.servlet.annotation.*;import jakarta.servlet.http.*;import java.io.*;import java.sql.*;
@WebServlet("/login") public class LoginServlet extends HttpServlet{
 protected void doPost(HttpServletRequest r,HttpServletResponse p)throws IOException{try(Connection c=DBConnection.getConnection();PreparedStatement s=c.prepareStatement("SELECT id,username FROM users WHERE (username=? OR email=?) AND password=?")){
 String v=r.getParameter("identity");s.setString(1,v);s.setString(2,v);s.setString(3,PasswordUtil.hash(r.getParameter("password")));ResultSet z=s.executeQuery();
 if(z.next()){if(r.getSession(false)!=null)r.getSession(false).invalidate();HttpSession h=r.getSession();h.setAttribute("userId",z.getInt(1));h.setAttribute("username",z.getString(2));p.sendRedirect("dashboard");}else{r.getSession().setAttribute("msg","Invalid login details.");p.sendRedirect("login.jsp");}
 }catch(Exception e){r.getSession().setAttribute("msg","Login error: "+e.getMessage());p.sendRedirect("login.jsp");}}
}
