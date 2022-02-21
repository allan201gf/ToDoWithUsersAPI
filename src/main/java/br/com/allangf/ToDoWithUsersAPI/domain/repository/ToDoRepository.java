package br.com.allangf.ToDoWithUsersAPI.domain.repository;

import br.com.allangf.ToDoWithUsersAPI.domain.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {

}
