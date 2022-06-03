package com.alkemy.ong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.alkemy.ong.models.response.CommentResponse;
import com.alkemy.ong.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@GetMapping("/comments")
	public ResponseEntity<List<CommentResponse>> getComments(){
		return ResponseEntity.ok(commentService.getCommentsByDate());
	}
}
