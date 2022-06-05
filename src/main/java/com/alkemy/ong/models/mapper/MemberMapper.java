package com.alkemy.ong.models.mapper;

import com.alkemy.ong.models.entity.MemberEntity;
import com.alkemy.ong.models.request.MemberRequest;
import com.alkemy.ong.models.response.MemberBasicResponse;
import com.alkemy.ong.models.response.MemberResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberMapper {
    public MemberEntity memberDTO2Entity(MemberRequest dto) {
        MemberEntity entity = new MemberEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        return entity;
    }

    public MemberResponse memberEntity2DTO(MemberEntity entity) {
        return MemberResponse.builder()
                .name(entity.getName())
                .description(entity.getDescription())
                .image(entity.getImage())
                .build();
    }

    public void memberEntityRefreshValues(MemberEntity entity, MemberRequest dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
    }
    public List<MemberBasicResponse> memberEntityList2DTOList(List<MemberEntity> entities){
        List<MemberBasicResponse> dtos = new ArrayList<>();

        for (MemberEntity memberEntity : entities) {
            MemberBasicResponse basicDto = new MemberBasicResponse();
            basicDto.setName(memberEntity.getName());
            dtos.add(basicDto);

        }
        return dtos;

    }
}
