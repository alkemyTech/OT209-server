package com.alkemy.ong.service;

import com.alkemy.ong.exception.NotAcceptableArgumentException;
import com.alkemy.ong.exception.OrgNotFoundException;
import com.alkemy.ong.models.request.SlideGetDTO;
import com.alkemy.ong.models.request.SlideRequestDTO;
import com.alkemy.ong.models.response.SlideBasicResponse;
import com.alkemy.ong.models.response.SlideResponse;
import java.io.IOException;
import java.util.List;

public interface SlideService {

    public SlideGetDTO create(SlideRequestDTO slideDTO) throws NotAcceptableArgumentException, IOException;
    public String delete(Long id) throws OrgNotFoundException;
    public SlideGetDTO updateSlide(Long id, SlideRequestDTO slideRequestDTO) throws OrgNotFoundException, NotAcceptableArgumentException, IOException ;
    public List<SlideBasicResponse> getSlides();
    public SlideResponse getSlideById(Long id);
}
