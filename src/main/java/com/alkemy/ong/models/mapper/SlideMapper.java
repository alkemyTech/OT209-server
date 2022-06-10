package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.Slide;
import com.alkemy.ong.models.request.SlideDTO;
import com.alkemy.ong.models.request.SlideGetDTO;
import com.alkemy.ong.models.request.SlidesGetDTO;
import com.amazonaws.SdkClientException;
import org.springframework.stereotype.Component;

import java.io.IOException;

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

    public SlideGetDTO getSlide(Slide slide){
        SlideGetDTO slideDTO= new SlideGetDTO();
        slideDTO.setImageUrl(slide.getImageUrl());
        slideDTO.setOrder(slide.getSlideOrder());
        slideDTO.setOrganizationId(slide.getOrganizationId());
        slideDTO.setText(slide.getText());
        return slideDTO;
    }

    public SlidesGetDTO getSlides(Slide slide, SlidesGetDTO slidesGetDTO){
        slidesGetDTO.setImageUrl(slide.getImageUrl());
        slidesGetDTO.setOrder_ong(slide.getSlideOrder());

        return slidesGetDTO;
    }
}
