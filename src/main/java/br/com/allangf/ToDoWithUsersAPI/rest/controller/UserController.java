package br.com.allangf.ToDoWithUsersAPI.rest.controller;


import br.com.allangf.ToDoWithUsersAPI.domain.entity.User;
import br.com.allangf.ToDoWithUsersAPI.rest.config.jwt.JwtService;
import br.com.allangf.ToDoWithUsersAPI.rest.dto.CredentialsDTO;
import br.com.allangf.ToDoWithUsersAPI.rest.dto.TokenDTO;
import br.com.allangf.ToDoWithUsersAPI.rest.dto.UserDTO;
import br.com.allangf.ToDoWithUsersAPI.rest.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private JwtService jwtService;

    @ApiOperation("Create new user")
    @PostMapping("/v1")
    public User createNewUser(@Valid @RequestBody UserDTO credentialsDTO) {
        return userService.createNewUser(credentialsDTO);
    }

    @ApiOperation("All user")
    @GetMapping("/v1")
    public List<User> allUser() {
        return userService.allUser();
    }

    @ApiOperation("Delete user logged")
    @DeleteMapping("/v1/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser() {
        userService.deleteUser();
    }

    @ApiOperation("Login")
    @PostMapping("/v1/login")
    public TokenDTO authenticate(@RequestBody @Valid CredentialsDTO credentialsDTO) {
        return userService.authenticate(credentialsDTO);
    }

    @ApiOperation("Get logged user")
    @GetMapping("/v1/getloggeduser")
    public Optional<User> getUserLogged() {
        return userService.getUserLogged();
    }

}
