package com.alkemy.ong.models.mapper;

import java.util.ArrayList;
import java.util.List;

import com.alkemy.ong.models.request.CommentRequest;
import com.alkemy.ong.models.response.CommentDetailsResponse;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.ong.models.entity.CommentEntity;

import com.alkemy.ong.models.response.CommentResponse;

@Component
@RequiredArgsConstructor
@AllArgsConstructor
public class CommentMapper {

	private CommentRepository commentRepository;


	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private UserRepository userRepository;

	public List<CommentResponse> listEntityToDtoList(List<CommentEntity> entityList) {
		List<CommentResponse> dtoList = new ArrayList<>();

		for (CommentEntity entity : entityList) {
			dtoList.add(new CommentResponse(entity.getBody()));
		}
		return dtoList;
	}


	public CommentEntity updateCommentsEntityWithCommentRequest(CommentEntity entity, CommentRequest request){
		entity.setId(request.getId());
		entity.setBody(request.getBody());
		
		entity.setDate(request.getDate());
		return entity;
	}



	public CommentEntity commentRequest2newsEntity( CommentRequest request){
		CommentEntity entity = new CommentEntity();
		entity.setId(request.getId());
		entity.setBody(request.getBody());
		entity.setNews(newsRepository.findById(request.getNewsId()).get());
		entity.setUser(userRepository.findById(request.getUserId()).get());
		entity.setDate(request.getDate());
		return entity;
	}


	public CommentResponse commentsEntity2CommentsResponse(CommentEntity comment){
		return CommentResponse.builder()
				.body(comment.getBody())
				.build();

	}

}
