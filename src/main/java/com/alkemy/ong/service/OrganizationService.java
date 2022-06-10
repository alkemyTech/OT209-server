
package com.alkemy.ong.service;

import com.alkemy.ong.exception.OrgNotFoundException;
import com.alkemy.ong.models.response.DateOrganizationResponse;
import com.alkemy.ong.models.response.OrganizationDTO;
import javassist.NotFoundException;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;


public interface OrganizationService {
    List<DateOrganizationResponse> getOrganizationInfo();

    OrganizationDTO update(Long id, OrganizationDTO dto) throws OrgNotFoundException;
}
