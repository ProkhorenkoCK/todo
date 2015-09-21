package com.my.todo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.todo.bean.ResultStatus;
import com.my.todo.bean.Todo;
import com.my.todo.manager.TodoManager;
import com.my.user.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/todo")
public class TodoController {

    @Autowired private TodoManager todoManager;

    @RequestMapping
    public String getTodo() {
        return "todo";
    }

    @ResponseBody
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public String getTodoList(User user) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(todoManager.getTodoList(user.getId()));
    }

    @ResponseBody
    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
    public String changeStatus(Integer todoId, boolean status, User user) {
        boolean isUpdated = todoManager.changeStatus(todoId, user.getId(), status);
        return isUpdated ? ResultStatus.SUCCESS.getMessage() : ResultStatus.ERROR.getMessage();
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Todo todo, User user) throws JsonProcessingException {
        todo.setUser(user); // attaching a user to instance
        Todo savedTodo = todoManager.save(todo);
        return new ObjectMapper().writeValueAsString(savedTodo);
    }

    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(Integer todoId, User user) {
        boolean isRemoved = todoManager.remove(todoId, user.getId());
        return isRemoved ? ResultStatus.SUCCESS.getMessage() : ResultStatus.ERROR.getMessage();
    }
}
