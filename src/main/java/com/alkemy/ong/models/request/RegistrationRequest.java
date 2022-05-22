package com.alkemy.ong.models.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}