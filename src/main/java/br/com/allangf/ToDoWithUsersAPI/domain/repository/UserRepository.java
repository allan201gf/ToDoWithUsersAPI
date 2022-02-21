package br.com.allangf.ToDoWithUsersAPI.domain.repository;

import br.com.allangf.ToDoWithUsersAPI.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
