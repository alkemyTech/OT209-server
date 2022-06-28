package com.alkemy.ong.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ContactResponse {

    private Long id;
    private String name;
    private String email;
    private String message;
    private String phone;

}
