
package com.alkemy.ong.service.impl;

import com.alkemy.ong.models.entity.Slide;
import com.alkemy.ong.models.mapper.OrganizationMapper;
import com.alkemy.ong.models.response.DateOrganizationResponse;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.OrganizationService;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper ongMapper;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private SlideRepository slideRepository;
    
    @Override
    public List<DateOrganizationResponse> getOrganizationInfo() {
     try {
         return organizationRepository.findAll().stream()
                .map( i -> ongMapper.ongToDto(i, slideRepository.findByOrganizationIdIs(i.getId())) )
                .collect(Collectors.toList());   
    }catch(Exception e){
         throw new EntityNotFoundException("Error getting organization data" + e.getMessage());
     }
    }  

}
