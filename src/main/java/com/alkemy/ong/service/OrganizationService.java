package com.alkemy.ong.service;

import com.alkemy.ong.exception.OrgNotFoundException;
import com.alkemy.ong.models.request.DateOrganizationRequest;
import com.alkemy.ong.models.response.DateOrganizationResponse;
import com.alkemy.ong.models.response.OrganizationDTO;
import java.util.List;

public interface OrganizationService {

    List<DateOrganizationResponse> getOrganizationInfo();

    OrganizationDTO update(Long id, OrganizationDTO dto) throws OrgNotFoundException;

    public String create(DateOrganizationRequest request);
}
