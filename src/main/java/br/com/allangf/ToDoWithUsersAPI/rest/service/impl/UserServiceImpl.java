package br.com.allangf.ToDoWithUsersAPI.rest.service.impl;

import br.com.allangf.ToDoWithUsersAPI.domain.entity.User;
import br.com.allangf.ToDoWithUsersAPI.domain.exception.PasswordInvalidOfException;
import br.com.allangf.ToDoWithUsersAPI.domain.exception.RuleOfException;
import br.com.allangf.ToDoWithUsersAPI.domain.repository.UserRepository;
import br.com.allangf.ToDoWithUsersAPI.rest.Errors;
import br.com.allangf.ToDoWithUsersAPI.domain.enums.RolesEnum;
import br.com.allangf.ToDoWithUsersAPI.rest.config.jwt.JwtService;
import br.com.allangf.ToDoWithUsersAPI.rest.dto.CredentialsDTO;
import br.com.allangf.ToDoWithUsersAPI.rest.dto.TokenDTO;
import br.com.allangf.ToDoWithUsersAPI.rest.dto.UserDTO;
import br.com.allangf.ToDoWithUsersAPI.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;

    public User createNewUser(UserDTO userDTO) {

        Optional<User> existUser = userRepository.findByEmail(userDTO.getEmail());

        if (existUser.isPresent()) {
            throw new RuleOfException(Errors.EMAIL_ALREADY_REGISTERED);
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(RolesEnum.USER.toString());
        user.setName(userDTO.getName());

        return userRepository.save(user);
    }

    @Override
    public List<User> allUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser() {
        try {
            Optional<User> loggedUser = getUserLogged();

            userRepository.deleteById(loggedUser.get().getUserId());
        } catch (EmptyResultDataAccessException e) {
            throw new RuleOfException(Errors.USER_NOT_FOUND);
        } catch (Exception e) {
            throw new RuleOfException(Errors.UNABLE_DELETE_USER_ASSIGNED_POST);
        }
    }

    @Override
    public TokenDTO authenticate(CredentialsDTO credentialsDTO) {
        try {
            User user = User.builder()
                    .email(credentialsDTO.getEmail())
                    .password(credentialsDTO.getPassword())
                    .build();
            UserDetails authenticatedUser = userDetailsService.authenticate(user);

            String token = jwtService.generateToken(user);

            return new TokenDTO(user.getEmail(), token);
        } catch (UsernameNotFoundException | PasswordInvalidOfException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    public Optional<User> getUserLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName());
    }
}
