package com.alkemy.ong.models.request;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class UserRequest {
   
    private Long id;  
    @NotBlank(message = "the name can't  be blank")   
    private String firstName;
    @NotBlank(message = "the lastName can't  be blank")   
    private String lastName;
 
    @Email(message = "enter a correct email")  
    private String email;
    
    @NotEmpty(message = "the password can't be null")
    private String password;

    private String photo;

  
}
