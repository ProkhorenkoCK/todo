package com.my.controller;

import com.my.user.bean.User;
import com.my.manager.UserSessionManager;
import com.my.user.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private UserManager userManager;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(String name, HttpSession session, HttpServletResponse response) throws IOException {
        User user = userManager.getUserByName(name);
        if (user != null) {
            session.setAttribute(UserSessionManager.USER_SESSION_ATTRIBUTE, user);
            response.sendRedirect("/todo/");
        } else {
            response.sendRedirect("/auth/login");
        }
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.setAttribute(UserSessionManager.USER_SESSION_ATTRIBUTE, null);
        response.sendRedirect("/");
    }
}
