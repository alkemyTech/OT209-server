package com.alkemy.ong.models.response;

import lombok.*;

@Getter
@Setter
@Builder
public class MemberResponse {
    private String name;

    private String description;

    private String image;
}
