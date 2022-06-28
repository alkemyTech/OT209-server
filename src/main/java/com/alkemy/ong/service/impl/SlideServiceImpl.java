package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.NotAcceptableArgumentException;
import com.alkemy.ong.exception.OrgNotFoundException;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.models.entity.Slide;
import com.alkemy.ong.models.mapper.SlideMapper;
import com.alkemy.ong.models.request.SlideDTO;
import com.alkemy.ong.models.request.SlideGetDTO;
import com.alkemy.ong.models.request.SlideRequestDTO;
import com.alkemy.ong.models.response.SlideBasicResponse;
import com.alkemy.ong.models.response.SlideResponse;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.AmazonClient;
import com.alkemy.ong.service.SlideService;
import com.alkemy.ong.utils.Base64MultipartFile;
import com.alkemy.ong.utils.ImageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class SlideServiceImpl implements SlideService {

    @Autowired
    SlideMapper slideMapper;

    @Autowired
    private SlideRepository slideRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    Base64MultipartFile base64MultipartFile;

    @Autowired
    ImageHelper imageHelper;

    @Autowired
    private AmazonClient amazonClient;

    @Override
    public SlideGetDTO create(SlideRequestDTO slideDTO) throws NotAcceptableArgumentException, IOException {
        if (slideDTO.getOrder() == null) {
            try {
                Integer ultOrder = (slideRepository.findAll().get(slideRepository.findAll().size() - 1).getSlideOrder()) + 1;
                slideDTO.setOrder(ultOrder);
            } catch (IndexOutOfBoundsException e) {
                //Lista vacia, se asigna el valor de uno a order
                slideDTO.setOrder(1);
            }
        }
        //validacion de inputs
        if (slideRepository.existsBySlideOrder(slideDTO.getOrder())) {
            //Orden ya existe
            throw new NotAcceptableArgumentException("Order already exists on the slide.");
        } else if (slideDTO.getOrganizationId() == 0) {
            throw new NotAcceptableArgumentException("You must enter a non-zero value for the Id organization field");
        } else if (!organizationRepository.existsById(slideDTO.getOrganizationId())) {
            //validacion si organizacion existe
            throw new NotAcceptableArgumentException("Organization doesn't exist ");
        } else {
            String nameFile = "Slide_" + slideDTO.getOrganizationId() + slideDTO.getOrder() + ".png";
            MultipartFile multiparte = imageHelper.base64ToImage(slideDTO.getEncodeImg(), nameFile);
            String amazonUrl = amazonClient.uploadFile(multiparte);
            slideDTO.setImageUrl(amazonUrl);
            SlideDTO slideD = new SlideDTO();
            slideD.setImageUrl(slideDTO.getImageUrl());
            slideD.setOrder(slideDTO.getOrder());
            slideD.setOrganizationId(organizationRepository.findById(slideDTO.getOrganizationId()).get().getId());
            slideD.setText(slideDTO.getText());
            Slide slide = slideMapper.slideDtoToEntity(slideD);
            slideRepository.save(slide);
            return slideMapper.slideEntityDto(slide);
        }
    }

    @Override
    public String delete(Long id) throws OrgNotFoundException {
        if (slideRepository.existsById(id)) {
            slideRepository.deleteById(id);
            return "Slide with the id " + id + " was deleted.";
        } else {
            throw new OrgNotFoundException(id, "Slide");
        }
    }

    @Override
    public SlideGetDTO updateSlide(Long id, SlideRequestDTO slideRequestDTO) throws OrgNotFoundException, NotAcceptableArgumentException, IOException {
        Long organizationId = slideRequestDTO.getOrganizationId();

        if (slideRepository.existsById(id)) {
            Slide s = slideRepository.findById(id).get();
            SlideDTO slideDTO = new SlideDTO(s.getImageId(), s.getImageUrl(), s.getSlideOrder(), s.getText(),
                    s.getOrganizationId());

            if (!(slideRequestDTO.getOrder() == null)) {
                if (slideRepository.existsBySlideOrder(slideRequestDTO.getOrder())) {
                    throw new NotAcceptableArgumentException("Order already exists on the slide");
                } else {
                    slideDTO.setOrder(slideRequestDTO.getOrder());
                }
            }
            if (!(organizationId == 0)) {
                if (organizationRepository.existsById(slideRequestDTO.getOrganizationId())) {
                    slideDTO.setOrganizationId(organizationRepository.findById(slideRequestDTO.getOrganizationId()).get().getId());
                } else {
                    throw new NotAcceptableArgumentException("Organization doesn't exist ");
                }
            }
            if (!(slideRequestDTO.getText() == null)) {

                slideDTO.setText(slideRequestDTO.getText());
            }       
            Slide slideUpdated = slideMapper.slideDtoToEntity(slideDTO);
            slideUpdated.setImageId(slideDTO.getId());
            slideRepository.save(slideUpdated);
            SlideGetDTO slideDto = slideMapper.slideEntityDto(slideUpdated);
            return slideDto;

        } else {
            throw new OrgNotFoundException(id, "Slide");
        }
    }

    @Override
    public List<SlideBasicResponse> getSlides() {
        List<Slide> entities = slideRepository.findAll();
        List<SlideBasicResponse> dtos = slideMapper.slideEntityList2DTOList(entities);
        return dtos;
    }

    @Override
    public SlideResponse getSlideById(Long id) {
        Optional<Slide> entity = slideRepository.findById(id);

        if (!entity.isPresent()) {
            throw new ParamNotFound(String.format("Id %s no encontrado en Slides", id));
        }
        return slideMapper.slideEntity2DTO(entity.get());
    }
}
