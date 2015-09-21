package com.my.manager;

import com.my.user.bean.User;
import org.springframework.stereotype.Component;

/**
 * Provides a class for getting logged user
 */
@Component
public final class UserSessionManager {
    public static final String USER_SESSION_ATTRIBUTE = "user";

    private static ThreadLocal<User> currentUser = new ThreadLocal<>();

    public void setLoggedUser(User user) {
        currentUser.set(user);
    }

    public User getLoggedUser() {
        return currentUser.get();
    }

    public void removeLoggedUser() {
        currentUser.remove();
    }
}
