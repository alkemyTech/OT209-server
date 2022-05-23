
package com.alkemy.ong.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class DateOrganizationResponse {
    private String name;
    private String image;   
    private String address;
    private Integer phone; 
}
