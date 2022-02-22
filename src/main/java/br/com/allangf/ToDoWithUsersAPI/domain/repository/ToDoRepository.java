package br.com.allangf.ToDoWithUsersAPI.domain.repository;

import br.com.allangf.ToDoWithUsersAPI.domain.entity.ToDo;
import br.com.allangf.ToDoWithUsersAPI.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {

    @Query("select p from ToDo p where p.priority like :priority and p.user like :user")
    List<ToDo> allToDoOrderByPriorityAndUser(String priority, User user);

    @Query("select p from ToDo p where p.user like :user")
    List<ToDo> allToDoByUser(User user);

}
