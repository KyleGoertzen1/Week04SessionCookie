/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import login.User;

/**
 *
 * @author 729347
 */
public class UserService {
    public User login(String username, String password){
       User user = new User();
       if((username.equals("adam") || username.equals("betty")) && password.equals("password")){
           user.setUsername(username);
           user.setPassword(null);
           return user;
       }
       return null;
    }
}
