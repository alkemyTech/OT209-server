package com.alkemy.ong.models.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "members")
@ApiModel("Model Member")
public class MemberEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @NotNull(message = "The name can't be null")
    @NotEmpty(message = "The name can't be empty")
    @ApiModelProperty("Member name")
    private String name;

    @NotBlank
    @NotNull(message = "The image can't be null")
    @NotEmpty(message = "The image can't be empty")
    @ApiModelProperty("imagen member")
    private String image;
    @ApiModelProperty("Description the member")
    private String description;

   @CreationTimestamp
    private Timestamp timestamp;

    @Column(name = "soft_delete")
    private Boolean softDelete = Boolean.FALSE;

}
