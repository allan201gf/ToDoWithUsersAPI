package br.com.allangf.ToDoWithUsersAPI.rest.dto;

import br.com.allangf.ToDoWithUsersAPI.rest.Errors;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ToDoDTO {

    @NotNull(message = Errors.DESCRIPTION_IS_REQUIRED)
    private String description;

    @NotNull(message = Errors.PRIORITY_IS_REQUIRED)
    private String priority;

}
