package com.my.user.manager;

import com.my.user.bean.User;
import com.my.user.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserManager {

    @Autowired
    private UserDao userDao;

    public User getUserByName(String name) {
        return userDao.getByName(name);
    }
}
