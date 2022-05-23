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

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "members")
@SQLDelete(sql = "UPDATE categories SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete = false")
public class MemberEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull(message = "The name can't be null")
    @NotEmpty(message = "The name can't be empty")
    @Column(nullable = false)
    private String name;

    @Column
    private String facebookUrl;

    @Column
    private String instagramUrl;

    @Column
    private String linkedinUrl;

    @NotBlank
    @NotNull(message = "The image can't be null")
    @NotEmpty(message = "The image can't be empty")
    @Column(nullable = false)
    private String image;

    @Column
    private String description;

    @Column @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private Boolean softDelete = Boolean.FALSE;
}
