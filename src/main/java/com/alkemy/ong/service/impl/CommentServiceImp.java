package com.alkemy.ong.service.impl;

import java.util.List;
import java.util.Optional;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.models.entity.CommentEntity;
import com.alkemy.ong.models.entity.UserEntity;
import com.alkemy.ong.models.request.CommentRequest;
import com.alkemy.ong.models.response.UserResponse;
import com.alkemy.ong.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.alkemy.ong.models.mapper.CommentMapper;
import com.alkemy.ong.models.response.CommentResponse;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.service.CommentService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private NewsService newsServiceimpl;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthServiceImpl authService;

    @Override
    public List<CommentResponse> getCommentsByDate() {
        List<CommentResponse> commentDto = commentMapper.listEntityToDtoList(commentRepository.findAllByOrderByDateDesc());
        return commentDto;
    }

    @Override
    @Transactional
    public CommentResponse create(CommentRequest commentRequest) throws ParamNotFound {

        if (!newsServiceimpl.itExists(commentRequest.getNewsId())) {
            throw new ParamNotFound("news ID not found");
        }
        if (!userService.ExistsUserById(commentRequest.getUserId())) {
            throw new ParamNotFound("User ID not found");
        }

        return commentMapper.commentsEntity2CommentsResponse(commentRepository.save(commentMapper.commentRequest2newsEntity(commentRequest)));
    }

    @Override
    public CommentResponse updateById(Long id, CommentRequest request, String authorization) throws AccessDeniedException {
        CommentEntity entity = commentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "the searched comment does not exist")); 
        checkComment(entity, request, authorization);
        return commentMapper.commentsEntity2CommentsResponse(
                commentRepository.save(commentMapper.updateCommentsEntityWithCommentRequest(entity, request)));
    }

    private void checkComment(CommentEntity entity, CommentRequest commentRequest, String authorization) throws AccessDeniedException {

        UserResponse userResponse = authService.userAuth(authorization);
        System.out.println(userResponse.getId() + "----------.dw.dw");
        UserEntity userEntity = userService.findById(userResponse.getId());

        if (userEntity.getRol().stream().anyMatch(
                e -> e.getName().equals("ROLE_ADMIN")
        )) {
            return;
        }

        if (!entity.getUser().getId().equals(commentRequest.getUserId())
                || !entity.getNews().getId().equals(commentRequest.getNewsId())
                || !(userResponse.getEmail().equals(entity.getUser().getEmail()))) {

            throw new AccessDeniedException("you do not have permissions to perform this action.");
        }

    }

    @Override
    public boolean itExists(Long id) {
        return commentRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id, String token) throws NotFoundException, AccessDeniedException {

        Optional<CommentEntity> commentEntity = commentRepository.findById(id);
        if (commentEntity.isEmpty()) {
            throw new NotFoundException("Comment not found");
        }
        UserResponse userResponse = authService.userAuth(token);
        UserEntity userEntity = userService.findById(userResponse.getId());

        if (userResponse.getEmail().equals(commentEntity.get().getUser().getEmail()) || userEntity.getRol().stream().anyMatch(
                e -> e.getName().equals("ROLE_ADMIN")
        )) {
            commentRepository.deleteById(id);
        } else {
            throw new AccessDeniedException("you do not have permissions to perform this action.");
        }

    }
}
