package br.com.allangf.ToDoWithUsersAPI.rest.service.impl;

import br.com.allangf.ToDoWithUsersAPI.domain.entity.User;
import br.com.allangf.ToDoWithUsersAPI.domain.exception.PasswordInvalidOfException;
import br.com.allangf.ToDoWithUsersAPI.domain.exception.RuleOfException;
import br.com.allangf.ToDoWithUsersAPI.domain.repository.UserRepository;
import br.com.allangf.ToDoWithUsersAPI.rest.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    public UserDetails authenticate(User user) {
        UserDetails userDetails = loadUserByUsername(user.getEmail());
        boolean passwordCorrect = encoder.matches(user.getPassword(), userDetails.getPassword());
        if (passwordCorrect) {
            return userDetails;
        }
        throw new PasswordInvalidOfException(Errors.PASSWORD_IS_WRONG);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new RuleOfException(Errors.USER_NOT_FOUND)
        );

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();

    }
}
