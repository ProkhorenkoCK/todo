package com.my.listener;

import com.my.user.bean.User;
import com.my.manager.UserSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class UserSessionListener implements ServletRequestListener {

    @Autowired
    private UserSessionManager userSessionManager;

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        userSessionManager.removeLoggedUser();
    }

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute(UserSessionManager.USER_SESSION_ATTRIBUTE);
            if (user != null) {
                userSessionManager.setLoggedUser(user);
            }
        }
    }
}
