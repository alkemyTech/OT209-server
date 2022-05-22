
package com.alkemy.ong.controller;

import com.alkemy.ong.models.response.DateOrganizationResponse;
import com.alkemy.ong.service.OrganizationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization")
@RequiredArgsConstructor
@CrossOrigin("*")
@Api(value = "Organization method", description = "This API has a dates the Organization")
public class OrganizationController {
    
    @Autowired
    private OrganizationService organizationService;
    @GetMapping("/public")
    public DateOrganizationResponse getOrganizationInfo(){
        return organizationService.getOrganizationInfo();
    }
}
