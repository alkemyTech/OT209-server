
package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.OrgNotFoundException;
import com.alkemy.ong.models.entity.Organization;
import com.alkemy.ong.models.mapper.OrganizationMapper;
import com.alkemy.ong.models.request.DateOrganizationRequest;
import com.alkemy.ong.models.response.DateOrganizationResponse;
import com.alkemy.ong.models.response.OrganizationDTO;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.OrganizationService;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
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
    @Autowired
    private SlideRepository slideRepository;
    
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrganizationMapper organizationMapper;

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

    @Override
    public OrganizationDTO update(Long id, OrganizationDTO dto) throws OrgNotFoundException {
        Organization model = this.organizationRepository.findById(id).orElse(null);

        if(model==null){
            throw new OrgNotFoundException(dto.getId(),"Organization");
        }
        model= this.organizationMapper.organizationRefreshValues(model, dto);
        Organization modelSaved = organizationRepository.save(model);
        OrganizationDTO result = organizationMapper.organizationModel2Dto(modelSaved);

        return result;
    }

	@Override
	public String create(DateOrganizationRequest request) {
		this.organizationRepository.save(this.ongMapper.orgRequestToEntity(request));
		return "Entity created";
	}  
}
