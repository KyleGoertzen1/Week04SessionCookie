/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import login.User;
import static servlets.LoginServlet.getCookieValue;

/**
 *
 * @author 729347
 */
public class MainPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            //session.setAttribute("userLogin", us.login(username, password));
            
            User user = (User) session.getAttribute("userLogin");
            
            if(user != null)
            {
                //stay in home page
                Cookie[] cookies = request.getCookies();
                request.setAttribute("username", getCookieValue(cookies, "LoggedIn"));
                getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
              //  response.sendRedirect("home");
                return;
            }
            else
            {
                Cookie[] cookies = request.getCookies();
                request.setAttribute("username", getCookieValue(cookies, "LoggedIn"));
                response.sendRedirect("login");
                return;
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
