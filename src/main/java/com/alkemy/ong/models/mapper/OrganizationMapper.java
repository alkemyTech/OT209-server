package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.Organization;
import com.alkemy.ong.models.entity.Slide;
import com.alkemy.ong.models.request.DateOrganizationRequest;
import com.alkemy.ong.models.response.DateOrganizationResponse;
import com.alkemy.ong.models.response.OrganizationDTO;
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
                .urlFacebook(ong.getUrlFacebook())
                .urlInstagram(ong.getUrlInstagram())
                .urlLinkedin(ong.getUrlLinkedin())
                .build();
    }

    public Organization orgRequestToEntity(DateOrganizationRequest request) {
    	Organization entity = new Organization();
		entity.setName(request.getName());
		entity.setImage(request.getImage());
		entity.setAddress(request.getAddress());
		entity.setPhone(Integer.parseInt(request.getPhone()));
		entity.setEmail(request.getEmail());
		entity.setWelcomeText(request.getWelcomeText());
		entity.setAboutUsText(request.getAboutUsText());
		entity.setUrlFacebook(request.getUrlFacebook());
		entity.setUrlInstagram(request.getUrlInstagram());
		entity.setUrlLinkedin(request.getUrlLinkedin());
    	return entity;
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

        model.setUrlInstagram(dto.getUrlInstagramDto());
        model.setUrlFacebook(dto.getUrlFacebookDto());
        model.setUrlLinkedin(dto.getUrlLinkedinDto());
        
        return model;
    }
    public OrganizationDTO organizationModel2Dto(Organization organization){

        OrganizationDTO dto = new OrganizationDTO();

        dto.setId(organization.getId());
        dto.setName(organization.getName());
        dto.setImage(organization.getImage());
        dto.setAddress(organization.getAddress());
        dto.setPhone(Integer.toString(organization.getPhone()));
        dto.setEmail(organization.getEmail());
        dto.setWelcomeText(organization.getWelcomeText());
        dto.setAboutUsText(organization.getAboutUsText());
        dto.setUrlFacebookDto(organization.getUrlFacebook());
        dto.setUrlInstagramDto(organization.getUrlInstagram());
        dto.setUrlLinkedinDto(organization.getUrlLinkedin());
        
        return dto;
    }

}
