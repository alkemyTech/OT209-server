package com.alkemy.ong.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Builder;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

@AllArgsConstructor
@Table(name = "users")
@NoArgsConstructor
@Data
@Entity
@SQLDelete(sql = "UPDATE User SET soft_delete = true WHERE id=?")
@FilterDef(
        name = "deletedUserFilter",
        parameters = @ParamDef(name = "isDeleted", type = "boolean")
)
@Filter(
        name = "deletedUserFilter",
        condition = "deleted = :isDeleted"
)
@Builder

public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NonNull
    @NotEmpty(message = "the name can't be null")
    @NotBlank(message = "the name can't  be blank")
    @Column(name = "first_name", nullable = false, updatable = false)
    private String firstName;

    @NonNull
    @NotEmpty(message = "the lastName can't be null")
    @NotBlank(message = "the lastName can't  be blank")
    @Column(name = "last_name", nullable = false, updatable = false)
    private String lastName;

    @NonNull
    @Email(message = "enter a correct email")
    @Column(nullable = false, updatable = false, unique = true)
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

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRol().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isEnabled(){
        return !this.softDelete;
    }

}
