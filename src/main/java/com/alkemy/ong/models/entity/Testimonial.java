package com.alkemy.ong.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "testimonials")
@Getter
@Setter
@Builder
@SQLDelete(sql = "UPDATE testimonials SET soft_delete = true WHERE testimonial_id=?")
@Where(clause = "soft_delete = false")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Model Testimonial")
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testimonial_id")
    private Long id;

    @NotBlank
    @NotNull(message = "the name can't be null")
    @NotEmpty(message = "the name can't be empty")   
    @ApiModelProperty(value = "The title of testimonials", required = true)
    private String name;
    @ApiModelProperty(value = "The image of testimonials", required = true)
    private String image;
    @NotBlank
    @NotNull(message = "the content can't be null")
    @NotEmpty(message = "the content can't be empty")
    @ApiModelProperty(value = "Text content of testimonials", required = true)
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime date;

    @Column(name = "soft_delete")
    private Boolean softDelete = Boolean.FALSE;
}
