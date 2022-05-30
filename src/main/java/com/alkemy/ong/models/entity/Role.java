package com.alkemy.ong.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@Table
@NoArgsConstructor
@Data
@Entity
public class Role {

    public Role(String name, String description, Timestamp timestamp) {
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
