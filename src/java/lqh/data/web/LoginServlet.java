/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package lqh.data.web;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lqh.data.dao.Database;
import lqh.data.models.User;


@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Login Page");
        request.getRequestDispatcher("./views/login.jsp").include(request, response);
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String emailphone = request.getParameter("emailphone");
        String password = request.getParameter("password");
        User user = Database.getUserDao().findUser(emailphone, password);
        
        if (user == null) {
            request.getSession().setAttribute("error_login", "You information is incorrect");
            response.sendRedirect("login");
        } else {
            if (user.getRole().equals("admin")) response.sendRedirect("admin");
            else {
                request.getSession().setAttribute("user", user);
                request.getSession().removeAttribute("error_login");
                response.sendRedirect("home");
            }
        }
        
    }

   
}
