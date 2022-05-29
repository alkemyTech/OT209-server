package com.alkemy.ong.models.response;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryResponse {
	private String name;
	
	private String description;
	
	private String image;
	
	@CreationTimestamp
	private Timestamp timestamp;
}
