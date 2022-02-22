package br.com.allangf.ToDoWithUsersAPI.rest.service.impl;

import br.com.allangf.ToDoWithUsersAPI.domain.entity.ToDo;
import br.com.allangf.ToDoWithUsersAPI.domain.entity.User;
import br.com.allangf.ToDoWithUsersAPI.domain.enums.PrioritiesEnum;
import br.com.allangf.ToDoWithUsersAPI.domain.enums.StatusEnum;
import br.com.allangf.ToDoWithUsersAPI.domain.exception.RuleOfException;
import br.com.allangf.ToDoWithUsersAPI.domain.repository.ToDoRepository;
import br.com.allangf.ToDoWithUsersAPI.domain.repository.UserRepository;
import br.com.allangf.ToDoWithUsersAPI.rest.Errors;
import br.com.allangf.ToDoWithUsersAPI.rest.dto.ToDoDTO;
import br.com.allangf.ToDoWithUsersAPI.rest.service.ToDoService;
import br.com.allangf.ToDoWithUsersAPI.rest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ToDoServiceImpl implements ToDoService {

    private final UserRepository userRepository;
    private final ToDoRepository toDoRepository;
    private final UserService userService;

    @Override
    public ToDo save(ToDoDTO toDoDTO) {

        ToDo toDo = new ToDo();
        toDo.setUser(userService.getUserLogged().orElseThrow(
                () -> new RuleOfException(Errors.USER_NOT_FOUND)
        ));
        toDo.setDescription(toDoDTO.getDescription());
        toDo.setStatus(StatusEnum.PENDING.toString());

        switch (toDoDTO.getPriority()) {
            case "HIGH":
                toDo.setPriority(PrioritiesEnum.HIGH.toString());
                break;
            case "MEDIUM":
                toDo.setPriority(PrioritiesEnum.MEDIUM.toString());
                break;
            case "LOW":
                toDo.setPriority(PrioritiesEnum.LOW.toString());
                break;
            default:
                throw new RuleOfException(Errors.PRIORITY_NOT_FOUND);
        }

        return toDoRepository.save(toDo);

    }

    @Override
    public ToDo alterTodo(int idTodo, ToDoDTO toDoDTO) {

        ToDo toDo = toDoRepository.findById(idTodo).orElseThrow(
                () -> new RuleOfException(Errors.INVALID_ID)
        );

        if (toDo.getUser().getUserId() != userService.getUserLogged().get().getUserId()) {
            throw new RuleOfException(Errors.TASK_FROM_ANOTHER_USER);
        }

        if (toDoDTO.getDescription() != null) {
            toDo.setDescription(toDoDTO.getDescription());
        }

        if (toDoDTO.getPriority() != null) {
            switch (toDoDTO.getPriority()) {
                case "HIGH":
                    toDo.setPriority(PrioritiesEnum.HIGH.toString());
                    break;
                case "MEDIUM":
                    toDo.setPriority(PrioritiesEnum.MEDIUM.toString());
                    break;
                case "LOW":
                    toDo.setPriority(PrioritiesEnum.LOW.toString());
                    break;
                default:
                    throw new RuleOfException(Errors.PRIORITY_NOT_FOUND);
            }
        }

        return toDoRepository.save(toDo);

    }

    @Override
    public void setStatus(int idToDo, String status) {

        ToDo toDo = toDoRepository.findById(idToDo).orElseThrow(
                () -> new RuleOfException(Errors.INVALID_ID)
        );


        if (toDo.getUser().getUserId() != userService.getUserLogged().get().getUserId()) {
            throw new RuleOfException(Errors.TASK_FROM_ANOTHER_USER);
        }

        switch (status) {
            case "PENDING":
                toDo.setStatus(StatusEnum.PENDING.toString());
                break;
            case "FINISHED":
                toDo.setStatus(StatusEnum.FINISHED.toString());
                break;
            default:
                throw new RuleOfException(Errors.STATUS_NOT_FOUND);
        }

        toDoRepository.save(toDo);
    }

    @Override
    public void deleteTodo(int idTodo) {
        ToDo toDo = toDoRepository.findById(idTodo).orElseThrow(
                () -> new RuleOfException(Errors.INVALID_ID)
        );

        if (toDo.getUser().getUserId() != userService.getUserLogged().get().getUserId()) {
            throw new RuleOfException(Errors.TASK_FROM_ANOTHER_USER);
        }

        toDoRepository.deleteById(idTodo);
    }

    @Override
    public List<ToDo> allToDo(boolean orderByPriority) {

        User user = userService.getUserLogged().get();

        if (orderByPriority) {
            List<ToDo> toDoList = new ArrayList<>();

            toDoList.addAll(toDoRepository.allToDoOrderByPriorityAndUser(PrioritiesEnum.HIGH.toString(), user));
            toDoList.addAll(toDoRepository.allToDoOrderByPriorityAndUser(PrioritiesEnum.MEDIUM.toString(), user));
            toDoList.addAll(toDoRepository.allToDoOrderByPriorityAndUser(PrioritiesEnum.LOW.toString(), user));

            return toDoList;
        }
        return toDoRepository.allToDoByUser(user);
    }

}
