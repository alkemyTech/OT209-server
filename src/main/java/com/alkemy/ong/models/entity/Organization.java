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

@AllArgsConstructor
@Table
@NoArgsConstructor
@Data
@Entity
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NonNull
    @NotEmpty(message = "the Name  can't be null")
    @NotBlank(message = "the Name can't  be blank")
    @Column(nullable = false)
    private String name;


    private String image;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Integer phone;



    @NotEmpty(message = "Welcome text cannot be null")
    @Column(nullable = false)
    private String welcomeText;

    @Column(nullable = false)
    @NotEmpty(message = "About text cannot be null")
    @NonNull
    private String aboutUsText;

    private Timestamp timestamp;

    private Boolean softDelete;

}
