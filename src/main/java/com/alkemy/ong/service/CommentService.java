package com.alkemy.ong.service;

import java.util.List;

import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.models.request.CommentRequest;
import com.alkemy.ong.models.response.CommentResponse;

public interface CommentService {

	public List<CommentResponse> getCommentsByDate();

	public CommentResponse updateById(Long id, CommentRequest request, String authorization);

	public boolean itExists(Long id) ;

	public CommentResponse create(CommentRequest commentRequest) throws ParamNotFound;

	public void deleteById(Long id, String token);

}
