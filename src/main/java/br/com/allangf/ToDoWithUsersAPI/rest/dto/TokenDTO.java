package br.com.allangf.ToDoWithUsersAPI.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {

    private String email;
    private String token;

}