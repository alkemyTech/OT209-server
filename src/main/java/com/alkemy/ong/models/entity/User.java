package com.alkemy.ong.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@Table(name = "users")
@NoArgsConstructor

public class User implements UserDetails{

    private static final long serialVersionUID = 1L;

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
    public User(
                   String firstName,
                   String lastName,
                   String email,
                   String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User( Long id,
                    String firstName,
                    String lastName,
                    String email,
                    String password) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
