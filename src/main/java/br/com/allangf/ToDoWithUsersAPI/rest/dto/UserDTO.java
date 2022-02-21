package br.com.allangf.ToDoWithUsersAPI.rest.dto;

import br.com.allangf.ToDoWithUsersAPI.rest.Errors;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UserDTO {

    @NotNull(message = Errors.EMAIL_IS_REQUIRED)
    @Email
    private String email;
    @NotNull(message = Errors.PASSWORD_IS_REQUIRED)
    private String password;

    @NotNull(message = Errors.NAME_IS_REQUIRED)
    private String name;

}
