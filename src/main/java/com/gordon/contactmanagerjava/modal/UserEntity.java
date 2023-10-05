package com.gordon.contactmanagerjava.modal;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_entity")
@ToString
@NamedQueries({
    @NamedQuery(name = "user_find_all", query = "FROM user_entity")
    ,@NamedQuery(name = "find_by_name", query = "FROM user_entity u WHERE u.firstName = :first_name")
//    ,@NamedQuery(name = "userEntity.findByFirstNameNamed", query = "FROM user_entity u WHERE u.firstName = :first_name")

})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

}
