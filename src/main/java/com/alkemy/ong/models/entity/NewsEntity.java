package com.alkemy.ong.models.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "news")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE news SET soft_delete = true WHERE news_id=?")
@Where(clause = "soft_delete = false")
@ApiModel("Model News")
public class NewsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "news_id")
	private Long id;
	
	@NotBlank
	@NotNull(message = "the name can't be null")
	@NotEmpty(message = "the name can't be empty")
	@ApiModelProperty("News title")
	private String name;
	
	@NotBlank
	@NotNull(message = "the content can't be null")
	@NotEmpty(message = "the content can't be empty")
	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;
	
	@NotBlank
	@NotNull(message = "the image can't be null")
	@NotEmpty(message = "the image can't be empty")
	@ApiModelProperty("News image")
	private String image;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
    @ApiModelProperty("Relationship category with news")
	private CategoryEntity category;
	//?
	@Column(name = "category_id", nullable = false)
	private Long categoryId;
	
	@CreationTimestamp	
	private Timestamp timestamp;
	
	@Column(name = "soft_delete")
	private Boolean softDelete = Boolean.FALSE; 
}
