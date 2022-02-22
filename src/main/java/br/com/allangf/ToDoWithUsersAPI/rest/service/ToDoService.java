package br.com.allangf.ToDoWithUsersAPI.rest.service;

import br.com.allangf.ToDoWithUsersAPI.domain.entity.ToDo;
import br.com.allangf.ToDoWithUsersAPI.rest.dto.ToDoDTO;

import java.util.List;

public interface ToDoService {

    ToDo save(ToDoDTO toDoDTO);

    ToDo alterTodo(int idTodo, ToDoDTO toDoDTO);

    void setStatus(int idTodo, String status);

    void deleteTodo(int idTodo);

    List<ToDo> allToDo(boolean orderByPriority);

}
