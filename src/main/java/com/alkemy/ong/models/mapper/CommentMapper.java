package com.alkemy.ong.models.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alkemy.ong.models.entity.CommentEntity;

import com.alkemy.ong.models.response.CommentResponse;

@Component
public class CommentMapper {

	public List<CommentResponse> listEntityToDtoList(List<CommentEntity> entityList) {
		List<CommentResponse> dtoList = new ArrayList<>();

		for (CommentEntity entity : entityList) {
			dtoList.add(new CommentResponse(entity.getBody()));
		}
		return dtoList;
	}
}
