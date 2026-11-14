package com.spidy.servlet;
import com.spidy.util.*;import jakarta.servlet.annotation.*;import jakarta.servlet.http.*;import java.io.*;import java.sql.*;
@WebServlet("/add-question") public class AddQuestionServlet extends HttpServlet{protected void doPost(HttpServletRequest r,HttpServletResponse p)throws IOException{
 if(!Auth.admin(r)){p.sendRedirect("admin-login.jsp");return;}String[] k={"title","description","category","difficulty","option_a","option_b","option_c","option_d","correct_answer"};
 try(Connection c=DBConnection.getConnection();PreparedStatement s=c.prepareStatement("INSERT INTO missions(title,description,category,difficulty,option_a,option_b,option_c,option_d,correct_answer,xp_reward,created_by) VALUES(?,?,?,?,?,?,?,?,?,?,?)")){
 for(int i=0;i<k.length;i++){String v=r.getParameter(k[i]);if(v==null||v.isBlank())throw new Exception("Please complete all fields.");s.setString(i+1,v);}s.setInt(10,Math.max(0,Integer.parseInt(r.getParameter("xp_reward"))));s.setInt(11,(Integer)r.getSession().getAttribute("adminId"));s.executeUpdate();r.getSession().setAttribute("msg","Mission saved.");p.sendRedirect("add-question.jsp");
 }catch(Exception e){r.getSession().setAttribute("msg",e.getMessage());p.sendRedirect("add-question.jsp");}}}
