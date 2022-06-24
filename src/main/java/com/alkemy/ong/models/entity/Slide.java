package com.alkemy.ong.models.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor  @NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "slides")
@ApiModel("Model Slide")
public class Slide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
 
    @NotNull(message ="El campo organizationId debe completarlo")
    @Column(name ="organizationId")
    private Long organizationId;
    @NotEmpty(message = "El campo imageUrl debe completarlo")
    @NotBlank
    @ApiModelProperty("Image direction")
    private String imageUrl;
    @ApiModelProperty("slider description")
    private String text;
    @ApiModelProperty("order number slide")
    private int slideOrder;

}
