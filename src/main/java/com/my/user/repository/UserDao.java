package com.my.user.repository;

import com.my.user.bean.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Integer>{

    User getByName(String name);
}
