package com.spidy.servlet;
import com.spidy.util.*;import jakarta.servlet.annotation.*;import jakarta.servlet.http.*;import java.io.*;import java.sql.*;
@WebServlet("/submit-answer") public class SubmitAnswerServlet extends HttpServlet{protected void doPost(HttpServletRequest r,HttpServletResponse p)throws IOException{
 if(!Auth.student(r)){p.sendRedirect("login.jsp");return;}int uid=(Integer)r.getSession().getAttribute("userId"),mid=Integer.parseInt(r.getParameter("missionId"));
 try(Connection c=DBConnection.getConnection()){c.setAutoCommit(false);try(PreparedStatement q=c.prepareStatement("SELECT correct_answer,xp_reward FROM missions WHERE id=?")){q.setInt(1,mid);ResultSet z=q.executeQuery();if(!z.next())throw new Exception("Mission not found");String ans=r.getParameter("answer");boolean ok=z.getString(1).equals(ans);int xp=z.getInt(2);
 try(PreparedStatement i=c.prepareStatement("INSERT INTO mission_submissions(user_id,mission_id,answer,is_correct) VALUES(?,?,?,?)")){i.setInt(1,uid);i.setInt(2,mid);i.setString(3,ans);i.setBoolean(4,ok);i.executeUpdate();}
 if(ok)try(PreparedStatement u=c.prepareStatement("UPDATE users SET total_xp=total_xp+?,player_level=FLOOR((total_xp+?)/100)+1 WHERE id=?")){u.setInt(1,xp);u.setInt(2,xp);u.setInt(3,uid);u.executeUpdate();}
 c.commit();r.getSession().setAttribute("result",ok?"Correct! You earned "+xp+" XP.":"Incorrect answer. No XP awarded.");p.sendRedirect("mission-result.jsp");}}
 catch(Exception e){c.rollback();r.getSession().setAttribute("result",e.getMessage().contains("Duplicate")?"You already attempted this mission.":"Submission failed: "+e.getMessage());p.sendRedirect("mission-result.jsp");}
 }catch(Exception e){r.getSession().setAttribute("result","Database error: "+e.getMessage());p.sendRedirect("mission-result.jsp");}}}
