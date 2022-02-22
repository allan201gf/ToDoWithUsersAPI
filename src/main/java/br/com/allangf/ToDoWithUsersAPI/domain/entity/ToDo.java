package br.com.allangf.ToDoWithUsersAPI.domain.entity;

import br.com.allangf.ToDoWithUsersAPI.domain.enums.PrioritiesEnum;
import br.com.allangf.ToDoWithUsersAPI.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idToDo;

    private String description;

    private String status;

    private String priority;

    @ManyToOne
    @JoinColumn(name = "user_id_user")
    private User user;

}
