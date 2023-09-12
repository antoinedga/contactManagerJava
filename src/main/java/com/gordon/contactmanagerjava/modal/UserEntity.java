package com.gordon.contactmanagerjava.modal;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_entity")
@ToString
@NamedQueries({
    @NamedQuery(name = "user_find_all", query = "FROM user_entity"),
    @NamedQuery(name = "find_by_name", query = "FROM user_entity u WHERE u.firstName = :first_name")
})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
