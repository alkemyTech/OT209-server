package com.alkemy.ong.models.entity;


import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Builder
@Data
@AllArgsConstructor  @NoArgsConstructor
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "slides")
public class Slide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;


    @NotEmpty(message ="El campo organizationId debe completarlo")
    @Column(name ="organizationId")
    private Long organizationId;


    @NotEmpty(message = "El campo imageUrl debe completarlo")
    @NotBlank
    @Column(nullable = false)
    private String imageUrl;

    //@NotEmpty(message = "El campo text debe completarlo")
    //@NotBlank
    @Column()
    private String text;

    @Column()
    private int slideOrder;

}
