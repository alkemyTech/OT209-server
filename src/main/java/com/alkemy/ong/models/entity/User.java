package com.alkemy.ong.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@AllArgsConstructor
@Table(name = "users")
@NoArgsConstructor
@Data
@Entity
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NonNull
    @NotEmpty(message = "the name can't be null")
    @NotBlank(message = "the name can't  be blank")
    @Column(name ="first_name",nullable = false , updatable = false)
    private String firstName;

    @NonNull
    @NotEmpty(message = "the lastName can't be null")
    @NotBlank(message = "the lastName can't  be blank")
    @Column(name= "last_name", nullable = false, updatable = false)
   private String lastName;

    @NonNull
    @Email(message = "enter a correct email")
    @Column(nullable = false,updatable = false, unique = true)
   private String email;

    @NonNull
    @Column(nullable = false)
    @NotEmpty(message = "the password can't be null")
   private String password;


   private String photo;

   private boolean softDelete;

    @Column
    private Timestamp timestamp;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
    joinColumns = {
            @JoinColumn(name = "User_id")
    },
    inverseJoinColumns = {
            @JoinColumn(name = "Role_id")
    })
    private Set<Role> rol;


}
