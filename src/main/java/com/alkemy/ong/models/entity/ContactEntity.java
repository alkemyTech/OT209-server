package com.alkemy.ong.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE news SET soft_delete = true WHERE news_id=?")
@Where(clause = "soft_delete = false")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Name can't be empty")
    @NotBlank
    @NotNull(message = "Name can't be null")
    private String name;

    @NotEmpty(message = "Email can't be empty")
    @NotBlank
    @NotNull(message = "Email can't be null")
    private String email;

    private String message;

    private String phone;

    @CreationTimestamp
    @Column
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private Boolean softDelete = Boolean.FALSE;
}
