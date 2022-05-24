package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.Organization;
import com.alkemy.ong.models.response.DateOrganizationResponse;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {
    public DateOrganizationResponse ongToDto(Organization ong){
        return DateOrganizationResponse.builder()
                .name(ong.getName())
                .image(ong.getImage())
                .address(ong.getAddress())
                .phone(String.valueOf(ong.getPhone()))                
                .build();
    }
}
