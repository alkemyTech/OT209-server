package com.alkemy.ong.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class ActivitiesDto {
    private String name;

    private String content;

    long organizationId;

    private String image;
}
