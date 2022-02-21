package br.com.allangf.ToDoWithUsersAPI.domain.exception;

import br.com.allangf.ToDoWithUsersAPI.rest.Errors;

public class PasswordInvalidOfException extends RuntimeException {

    public PasswordInvalidOfException(String message) {
        super(Errors.PASSWORD_IS_WRONG);
    }

}
