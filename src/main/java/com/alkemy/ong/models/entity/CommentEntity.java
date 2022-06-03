package com.alkemy.ong.models.entity;

import java.sql.Timestamp;
import java.util.Date;

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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comments")
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long id;

	@Column(nullable = false)
	private Long user_id;

	@NotBlank
	@NotNull(message = "The body can't be null.")
	@NotEmpty(message = "The body can't be empty.")
	@Column(nullable = false)
	private String body;

	@Column(nullable = false)
	private Long news_id;

	@Column(name = "date")
	@CreationTimestamp
	private Timestamp date;

	public CommentEntity(Long userId, String body, Long newsId) {
		this.user_id = userId;
		this.body = body;
		this.news_id = newsId;
	}
}
