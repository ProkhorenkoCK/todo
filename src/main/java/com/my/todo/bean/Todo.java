package com.my.todo.bean;


import com.my.bean.BaseEntity;
import com.my.user.bean.User;
import org.hibernate.annotations.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "todo")
public class Todo extends BaseEntity {

    private String task;
    private boolean complete;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
