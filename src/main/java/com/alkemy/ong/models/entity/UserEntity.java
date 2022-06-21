package com.alkemy.ong.models.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Builder;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.Set;
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
@ApiModel ("Model User")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NonNull
    @NotEmpty(message = "the name can't be null")
    @NotBlank(message = "the name can't  be blank")
    @Column(name = "first_name", nullable = false, updatable = false)
    @ApiModelProperty(value = "the first name user", required = true)
    private String firstName;

    @NonNull
    @NotEmpty(message = "the lastName can't be null")
    @NotBlank(message = "the lastName can't  be blank")
    @Column(name = "last_name", nullable = false, updatable = false)
    @ApiModelProperty(value = "the last name user", required = true)
    private String lastName;

    @NonNull
    @Email(message = "enter a correct email")
    @Column(nullable = false, updatable = false, unique = true)
    @ApiModelProperty(value = "the email user", required = true)
    private String email;

    @NonNull
    @NotEmpty(message = "the password can't be null")
    @ApiModelProperty(value = "the password user >=8 character ", required = true)
    private String password;

    private String photo;

    private boolean softDelete;

    @Column
    private Timestamp timestamp;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_roles",
            joinColumns = {
                @JoinColumn(name = "User_id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "Role_id")
            })
    private Set<RoleEntity> rol;

    public UserEntity(String cFirstName, String cLastName, String cEmail, String cPassword, String cPhoto, Timestamp cTimestamp, Set<RoleEntity> cRoles) {
    	this.setFirstName(cFirstName);
    	this.setLastName(cLastName);
    	this.setEmail(cEmail);
    	this.setPassword(cPassword);
    	this.setPhoto(cPhoto);
    	this.setTimestamp(cTimestamp);
    	this.setRol(cRoles);
    }

    public UserEntity(String ronald, String kathleen, String ronaldemailgmailcom, String encode) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
