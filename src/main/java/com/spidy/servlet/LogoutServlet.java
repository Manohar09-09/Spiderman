package com.spidy.servlet;import jakarta.servlet.annotation.*;import jakarta.servlet.http.*;import java.io.*;
@WebServlet("/logout") public class LogoutServlet extends HttpServlet{protected void doGet(HttpServletRequest r,HttpServletResponse p)throws IOException{if(r.getSession(false)!=null)r.getSession(false).invalidate();p.sendRedirect("index.jsp");}}
