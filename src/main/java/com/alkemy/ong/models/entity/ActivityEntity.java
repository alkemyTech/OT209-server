package com.alkemy.ong.models.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity @Table(name = "activities")
@SQLDelete(sql = "UPDATE categories SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete = false")
public class ActivityEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull(message = "The name can't be null")
    @NotEmpty(message = "The name can't be empty")
    @Column(nullable = false)
    private String name;


    @NotBlank
    @NotNull(message = "The content can't be null")
    @NotEmpty(message = "The content can't be empty")
    @Column(nullable = false)
    private String content;

    @NotBlank
    @NotNull(message = "The image can't be null")
    @NotEmpty(message = "The image can't be empty")
    @Column(nullable = false)
    private String image;

    @Column @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private Boolean softDelete = Boolean.FALSE;

    public ActivityEntity(String content1, String image1, String name1) {
        this.content=content1;
        this.image=image1;
        this.name=name1;
    }
}
