package com.alkemy.ong.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.models.mapper.CommentMapper;
import com.alkemy.ong.models.response.CommentResponse;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.service.CommentService;

@Service
public class CommentServiceImp implements CommentService{

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CommentMapper commentMapper;
	@Override
	public List<CommentResponse> getCommentsByDate() {
		List<CommentResponse> commentDto = commentMapper.listEntityToDtoList(commentRepository.findAllByOrderByDateDesc());
		return commentDto;
	}

}
