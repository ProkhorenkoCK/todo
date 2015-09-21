package com.my.todo.manager;

import com.my.todo.bean.Todo;
import com.my.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TodoManager {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getTodoList(Integer userId) {
        return todoRepository.getByUserId(userId);
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public boolean changeStatus(Integer todoId, Integer userId, boolean status) {
        int updateResult = todoRepository.changeStatus(todoId, userId, status);
        return updateResult > 0;
    }

    public boolean remove(Integer todoId, Integer userId) {
        int removeResult = todoRepository.deleteByIdAndUserId(todoId, userId);
        return removeResult > 0;
    }
}
