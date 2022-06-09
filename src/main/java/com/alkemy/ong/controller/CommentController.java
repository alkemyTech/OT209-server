package com.alkemy.ong.controller;

import java.util.List;

import com.alkemy.ong.models.request.CommentRequest;
import com.alkemy.ong.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.models.response.CommentResponse;
import com.alkemy.ong.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@GetMapping("/comments")
	public ResponseEntity<List<CommentResponse>> getComments(){
		return ResponseEntity.ok(commentService.getCommentsByDate());
	}


	@PostMapping("/comments")
	//TODO: refactorizar para controlador de errores
	public ResponseEntity<CommentResponse>  create(@Valid  @RequestBody CommentRequest commentRequest){

		CommentResponse response = commentService.create(commentRequest);
		return ResponseEntity.ok(response);

	}

	@PutMapping("/comments/{id}")
	public ResponseEntity<CommentResponse> update(@PathVariable Long id, @Valid @RequestBody CommentRequest commentRequest,
												  HttpServletRequest request){


		var a = commentService.updateById(id, commentRequest, request.getHeader("Authorization"));

		return null;

	}


}
