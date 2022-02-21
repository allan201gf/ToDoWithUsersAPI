package br.com.allangf.ToDoWithUsersAPI.rest.service;

import br.com.allangf.ToDoWithUsersAPI.domain.entity.User;
import br.com.allangf.ToDoWithUsersAPI.rest.dto.CredentialsDTO;
import br.com.allangf.ToDoWithUsersAPI.rest.dto.TokenDTO;
import br.com.allangf.ToDoWithUsersAPI.rest.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createNewUser(UserDTO ccredentialsDTO);

    List<User> allUser();

    void deleteUser();

    TokenDTO authenticate(CredentialsDTO credentialsDTO);

    Optional<User> getUserLogged();

}
