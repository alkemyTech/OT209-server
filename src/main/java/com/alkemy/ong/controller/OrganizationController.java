package com.alkemy.ong.controller;

import com.alkemy.ong.exception.OrgNotFoundException;
import com.alkemy.ong.models.request.DateOrganizationRequest;
import com.alkemy.ong.models.response.DateOrganizationResponse;
import com.alkemy.ong.models.response.OrganizationDTO;
import com.alkemy.ong.service.OrganizationService;
import io.swagger.annotations.Api;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/organization")
@RequiredArgsConstructor
@CrossOrigin("*")
@Api(value = "Organization method", description = "This API has a dates the Organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/public")
    public List<DateOrganizationResponse> getOrganizationInfo() {
        return organizationService.getOrganizationInfo();
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody DateOrganizationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.organizationService.create(request));
    }

    @PutMapping("/public/{id}")
    public ResponseEntity<Object> save(@PathVariable("id") Long id, @Valid @RequestBody OrganizationDTO dto) {

        OrganizationDTO dtoReturned = null;
        try {
            dtoReturned = this.organizationService.update(id, dto);
        } catch (OrgNotFoundException e) {

            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body(dtoReturned);
    }

}
