package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.CategoryEntity;
import com.alkemy.ong.models.entity.ContactEntity;
import com.alkemy.ong.models.request.ContactRequest;
import com.alkemy.ong.models.response.CategoryBasicResponse;
import com.alkemy.ong.models.response.ContactResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContactMapper {


    public ContactEntity request2Entity(ContactRequest request) {

        ContactEntity entity = new ContactEntity();
        entity.setName(request.getName());
        entity.setPhone(request.getPhone());
        entity.setEmail(request.getEmail());
        entity.setMessage(request.getMessage());

        return entity;
    }

    public List<ContactResponse> contactEntityList2ResponseList(List<ContactEntity> entities){
        List<ContactResponse> responses = new ArrayList<>();

        for (ContactEntity entity : entities) {
            responses.add(ContactResponse.builder()
                            .id(entity.getId())
                            .name(entity.getName())
                            .email(entity.getEmail())
                            .phone(entity.getPhone())
                            .message(entity.getMessage())
                    .build());
        }
        return responses;

    }


}
