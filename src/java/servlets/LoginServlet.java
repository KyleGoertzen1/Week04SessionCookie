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
import utilities.CookieUtil;

/**
 * Since the URL is coded to stop the user from going into the home page with
 * out logging in or going to the login when you already logged in we have to
 * use an action in our <a href> tag. This is done with
 * <a href=login?action=logout>Logout</a>
 * @author 729347
 */
public class LoginServlet extends HttpServlet {
    public static String getCookieValue(Cookie[] cookies, String cookieName){
        String cookieValue = "";
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookieName.equals(cookie.getName())){
                    cookieValue = cookie.getValue();
                }
            }
        }
        return cookieValue;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("userLogin");
            
            String action = request.getParameter("action");
            if(action != null && action.equals("logout")){
                request.setAttribute("error", true);
                request.setAttribute("logOutMessage", "You have successfully logged out.");
                session.invalidate();
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                //response.sendRedirect("login");
                return;
            }
            
            if(user != null){
                response.sendRedirect("home");
            } else {
                Cookie[] cookies = request.getCookies();
                request.setAttribute("username", getCookieValue(cookies, "LoggedIn"));
                //response.sendRedirect("login");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
            
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("uname");
        String password = request.getParameter("pword");
        String checkboxValue = request.getParameter("remember");
        
        UserService us = new UserService();
        
        if(us.login(username, password) != null){
            HttpSession session = request.getSession();
            session.setAttribute("userLogin", us.login(username, password));
            
            //User user = (User) session.getAttribute("user");
            
            if(checkboxValue != null){
                Cookie myCookie = new Cookie("LoggedIn", username);
                response.addCookie(myCookie);
                Cookie[] cookies = request.getCookies();
                String Login = 
                        CookieUtil.getCookieValue(cookies, "Login");
                request.setAttribute("username", username);
                response.sendRedirect("home");
                //getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
                return;
            } else {
                Cookie[] cookies = request.getCookies();
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals(username)){
                        cookie.setMaxAge(0);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                }
                request.setAttribute("username", username);
                response.sendRedirect("home");
                //getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
                return;
            }
        }
        
        if(us.login(username, password) == null){
            request.setAttribute("errorMessage", "Please enter correct data.");
            response.sendRedirect("login");
            //getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
    }
}
