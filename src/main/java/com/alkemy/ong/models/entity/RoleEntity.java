package com.alkemy.ong.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@Table
@NoArgsConstructor
@Data
@Entity
public class RoleEntity {

	public RoleEntity(String name, String description, Timestamp timestamp) {
		this.setName(name);
		this.setDescription(description);
		this.setTimestamp(timestamp);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@NonNull
	@Column(nullable = false)
	private String name;

	private String Description;

	@Column(name = "timestamp")
	private Timestamp timestamp;

}
