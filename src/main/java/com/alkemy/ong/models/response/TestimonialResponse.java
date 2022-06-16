package com.alkemy.ong.models.response;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.*;

@Setter @Builder @Getter @NoArgsConstructor @AllArgsConstructor
public class TestimonialResponse {
        private Long id;	
	private String name;	
	private String image;	
	private String content;
        private LocalDate date;
	private LocalTime hour;
}
