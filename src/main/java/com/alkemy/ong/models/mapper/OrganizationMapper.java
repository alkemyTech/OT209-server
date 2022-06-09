package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.Organization;
import com.alkemy.ong.models.entity.Slide;
import com.alkemy.ong.models.response.DateOrganizationResponse;
import com.alkemy.ong.utility.ComparatorSlide;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {
    public DateOrganizationResponse ongToDto(Organization ong, List<Slide> slides){
    	Collections.sort(slides, new ComparatorSlide());
        return DateOrganizationResponse.builder()
                .name(ong.getName())
                .image(ong.getImage())
                .address(ong.getAddress())
                .phone(String.valueOf(ong.getPhone()))
                .slides(slides)
                .build();
    }
}
