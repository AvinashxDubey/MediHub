package com.springproject.medihubdata.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String password;

//    @Enumerated(EnumType.STRING)
//    @Column(name="role", columnDefinition = "VARCHAR(255)")
//    private Role role; //like admin, nurse, doctor //not working maybe later
    private String role;


}
