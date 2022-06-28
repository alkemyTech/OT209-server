package com.alkemy.ong.controller;

import java.util.List;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.models.request.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    public ResponseEntity<List<CommentResponse>> getComments() {
        return ResponseEntity.ok(commentService.getCommentsByDate());
    }

    @PostMapping("/comments")
    //TODO: refactorizar para controlador de errores
    public ResponseEntity<CommentResponse> create(@Valid @RequestBody CommentRequest commentRequest) {

        System.out.println(commentRequest.getId() + "****************************" + commentRequest.getNewsId());
        CommentResponse response = commentService.create(commentRequest);
        return ResponseEntity.ok(response);

    }

    @PutMapping("/comments/{id}")
    @Transactional
    public ResponseEntity<CommentResponse> update(@PathVariable Long id, @Valid @RequestBody CommentRequest commentRequest,
            HttpServletRequest request) {

        try {
            CommentResponse comment = commentService.updateById(id, commentRequest, request.getHeader("Authorization"));
            return comment == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(comment);

        } catch (AccessDeniedException e) {

            return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest request) {

        try {

            commentService.deleteById(id, request.getHeader("Authorization"));
            return ResponseEntity.ok().build();

        } catch (NotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (AccessDeniedException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
