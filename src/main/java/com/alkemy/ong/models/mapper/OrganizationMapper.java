package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.Organization;
import com.alkemy.ong.models.entity.Slide;
import com.alkemy.ong.models.response.DateOrganizationResponse;

import com.alkemy.ong.utility.ComparatorSlide;

import java.util.Collections;
import java.util.List;

import com.alkemy.ong.models.response.OrganizationDTO;
import lombok.ToString;

import org.springframework.stereotype.Component;

import java.util.function.ToIntFunction;

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

    public Organization organizationRefreshValues(Organization model, OrganizationDTO dto){

        if(dto.getName()!=null && !dto.getName().isEmpty()){
            model.setName(dto.getName());
        }

        if(dto.getImage()!=null && !dto.getImage().isEmpty()){
            model.setImage(dto.getImage());
        }

        if (dto.getAddress()!=null && !dto.getAddress().isEmpty()){
            model.setAddress(dto.getAddress());
        }

        if (dto.getPhone()!= null){
            model.setPhone(Integer.parseInt(dto.getPhone()));
        }

        if (dto.getEmail()!=null && !dto.getEmail().isEmpty()){
            model.setEmail(dto.getEmail());
        }

        if (dto.getWelcomeText()!=null && !dto.getWelcomeText().isEmpty()){
            model.setWelcomeText(dto.getWelcomeText());
        }

        if (dto.getAboutUsText()!=null && !dto.getAboutUsText().isEmpty()){
            model.setAboutUsText(dto.getAboutUsText());
        }

        return model;
    }
    public OrganizationDTO organizationModel2Dto(Organization organization){

        OrganizationDTO dto = null;

        dto.setId(organization.getId());
        dto.setName(organization.getName());
        dto.setImage(organization.getImage());
        dto.setAddress(organization.getAddress());
        dto.setPhone(Integer.toString(organization.getPhone()));
        dto.setEmail(organization.getEmail());
        dto.setWelcomeText(organization.getWelcomeText());
        dto.setAboutUsText(organization.getAboutUsText());

        return dto;
    }

}
