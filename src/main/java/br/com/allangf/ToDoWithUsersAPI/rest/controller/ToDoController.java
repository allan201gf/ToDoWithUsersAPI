package br.com.allangf.ToDoWithUsersAPI.rest.controller;

import br.com.allangf.ToDoWithUsersAPI.domain.entity.ToDo;
import br.com.allangf.ToDoWithUsersAPI.rest.dto.ToDoDTO;
import br.com.allangf.ToDoWithUsersAPI.rest.service.ToDoService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/todo")
@AllArgsConstructor
public class ToDoController {

    private ToDoService toDoService;

    @ApiOperation("Create new todo")
    @PostMapping("/v1")
    public ToDo save(@RequestBody @Valid ToDoDTO toDoDTO) {
        return toDoService.save(toDoDTO);
    }

    @ApiOperation("Alter todo")
    @PutMapping("/v1/alter")
    public ToDo alterTodo(@RequestParam int id, @RequestBody ToDoDTO toDoDTO) {
        return toDoService.alterTodo(id, toDoDTO);
    }

    @ApiOperation("Alter status")
    @GetMapping("/v1/setstatus")
    public void setStatus(@RequestParam int id, @RequestParam String status) {
        toDoService.setStatus(id, status);
    }

    @ApiOperation("Delete todo")
    @DeleteMapping("/v1/delete")
    public void deleteTodo(@RequestParam int id) {
        toDoService.deleteTodo(id);
    }

    @ApiOperation("List all todo")
    @GetMapping("/v1/all")
    public List<ToDo> allToDo(@RequestParam boolean orderByPriority) {
        return toDoService.allToDo(orderByPriority);
    }

}
