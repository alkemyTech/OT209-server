package com.alkemy.ong.models.entity;

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
@Table(name = "testimonials")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE testimonials SET soft_delete = true WHERE testimonial_id=?")
@Where(clause = "soft_delete = false")
public class TestimonialEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "testimonial_id")
	private Long id;
	
	@NotBlank
	@NotNull(message = "the name can't be null")
	@NotEmpty(message = "the name can't be empty")
	@Column(nullable = false)
	private String name;
	
	@Column
	private String image;
	
	@Column
	private String content;

	@CreationTimestamp
	@Column
	private Timestamp timestamp;
	
	@Column(name = "soft_delete")
	private Boolean softDelete = Boolean.FALSE;
}
