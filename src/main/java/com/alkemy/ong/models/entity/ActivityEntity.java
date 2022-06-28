package com.alkemy.ong.models.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Builder;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity @Table(name = "activities")
@Builder
public class ActivityEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull(message = "The name can't be null")
    @NotEmpty(message = "The name can't be empty")
    @ApiModelProperty("Activity name")
    private String name;


    @NotBlank
    @NotNull(message = "The content can't be null")
    @NotEmpty(message = "The content can't be empty")
    @ApiModelProperty("activity content")
    private String content;

    @NotBlank
    @NotNull(message = "The image can't be null")
    @NotEmpty(message = "The image can't be empty")
    @ApiModelProperty("activity image")
    private String image;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private Boolean softDelete = Boolean.FALSE;
    public ActivityEntity(String content1, String image1, String name1) {
        this.content=content1;
        this.image=image1;
        this.name=name1;
    }
}
