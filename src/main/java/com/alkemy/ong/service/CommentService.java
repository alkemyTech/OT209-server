package com.alkemy.ong.service;

import java.util.List;

import com.alkemy.ong.models.response.CommentResponse;

public interface CommentService {

	public List<CommentResponse> getCommentsByDate();
}
