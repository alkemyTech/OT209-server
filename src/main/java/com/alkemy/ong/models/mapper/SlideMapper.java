package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.Slide;
import com.alkemy.ong.models.request.SlideDTO;
import com.alkemy.ong.models.request.SlideGetDTO;
import com.alkemy.ong.models.request.SlidesGetDTO;
import com.alkemy.ong.models.response.SlideBasicResponse;
import com.alkemy.ong.models.response.SlideResponse;
import com.amazonaws.SdkClientException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SlideMapper {

    public Slide slideDtoToEntity(SlideDTO slideDto) throws IOException {
        Slide slide = new Slide();
        slide.setText(slideDto.getText());
        slide.setImageUrl(slideDto.getImageUrl());
        slide.setSlideOrder(slideDto.getOrder());
        slide.setOrganizationId(slideDto.getOrganizationId());

        return slide;
    }

    public SlideGetDTO slideEntityDto(Slide slide) throws SdkClientException{
        SlideGetDTO slideGetDTO = new SlideGetDTO();
        slideGetDTO.setImageUrl(slide.getImageUrl());
        slideGetDTO.setText(slide.getText());
        slideGetDTO.setOrder(slide.getSlideOrder());
        slideGetDTO.setOrganizationId(slide.getOrganizationId());

        return slideGetDTO;
    }

    public List<SlideBasicResponse> slideEntityList2DTOList(List<Slide> entities){
    	List<SlideBasicResponse> dtos = new ArrayList<>();
    	for (Slide slideEntity : entities) {
			SlideBasicResponse basicDto = new SlideBasicResponse();
			basicDto.setImageUrl(slideEntity.getImageUrl());
			basicDto.setSlideOrder(slideEntity.getSlideOrder());
			dtos.add(basicDto);
		}
    	return dtos;
    }
    
    public SlideResponse slideEntity2DTO(Slide slide){
        SlideResponse dto= new SlideResponse();
        dto.setImageUrl(slide.getImageUrl());
        dto.setText(slide.getText());
        dto.setSlideOrder(slide.getSlideOrder());
        dto.setOrganizationId(slide.getOrganizationId());

        return dto;
    }
}
