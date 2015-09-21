package com.my.todo.repository;

import com.my.todo.bean.Todo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Integer> {
    List<Todo> getByUserId(Integer userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Todo t SET t.complete = :status WHERE t.id = :todoId AND t.userId = :userId ", nativeQuery = true)
    int changeStatus(@Param("todoId") Integer todoId, @Param("userId") Integer userId, @Param("status") boolean status);

    int deleteByIdAndUserId(Integer userId, Integer id);
}
