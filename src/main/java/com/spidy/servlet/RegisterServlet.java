package com.spidy.servlet;
import com.spidy.util.*;import jakarta.servlet.annotation.*;import jakarta.servlet.http.*;import java.io.*;import java.sql.*;
@WebServlet("/register") public class RegisterServlet extends HttpServlet{
 protected void doPost(HttpServletRequest r,HttpServletResponse p)throws IOException{
 String u=r.getParameter("username"),e=r.getParameter("email"),pw=r.getParameter("password");
 if(u==null||e==null||pw==null||u.isBlank()||e.isBlank()||pw.length()<6||!pw.equals(r.getParameter("confirmPassword"))){r.getSession().setAttribute("msg","Complete all fields; passwords must match and be at least 6 characters.");p.sendRedirect("register.jsp");return;}
 try(Connection c=DBConnection.getConnection();PreparedStatement s=c.prepareStatement("INSERT INTO users(username,email,password) VALUES(?,?,?)")){s.setString(1,u.trim());s.setString(2,e.trim());s.setString(3,PasswordUtil.hash(pw));s.executeUpdate();r.getSession().setAttribute("msg","Registration successful. Log in now.");p.sendRedirect("login.jsp");}
 catch(SQLIntegrityConstraintViolationException x){r.getSession().setAttribute("msg","Username or email already exists.");p.sendRedirect("register.jsp");}catch(Exception x){r.getSession().setAttribute("msg","Registration error: "+x.getMessage());p.sendRedirect("register.jsp");}}
}
