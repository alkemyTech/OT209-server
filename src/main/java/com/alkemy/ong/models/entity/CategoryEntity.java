package com.alkemy.ong.models.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE categories SET soft_delete = true WHERE category_id=?")
@Where(clause = "soft_delete = false")
@ApiModel("Model Category")
public class CategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long id;
	
	@NotBlank(message = "the name can't be blank")
	@NotNull(message = "the name can't be null")
	@NotEmpty(message = "the name can't be empty")
	@ApiModelProperty("Name category")
	private String name;
        @ApiModelProperty("Description category")
	private String description;
        @ApiModelProperty("Image category")
	private String image;	
	@CreationTimestamp
	private Timestamp timestamp;	
	@Column(name = "soft_delete")
	private Boolean softDelete = Boolean.FALSE;
	 
	
}