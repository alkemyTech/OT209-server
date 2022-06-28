package com.alkemy.ong.models.response;

import java.util.List;

import com.alkemy.ong.models.entity.Slide;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DateOrganizationResponse {

    private String name;
    private String image;
    private String address;
    private String phone;
    private List<Slide> slides;
    private String urlInstagram;
    private String urlFacebook;
    private String urlLinkedin;
}
