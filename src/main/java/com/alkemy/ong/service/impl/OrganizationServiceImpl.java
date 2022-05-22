
package com.alkemy.ong.service.impl;

import com.alkemy.ong.models.entity.Organization;
import com.alkemy.ong.models.mapper.OrganizationMapper;
import com.alkemy.ong.models.response.DateOrganizationResponse;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper ongMapper;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Override
    public DateOrganizationResponse getOrganizationInfo() {
      Organization o= organizationRepository.getById( 1L) ;
      return ongMapper.ongToDto(o) ;
    }
    
}
