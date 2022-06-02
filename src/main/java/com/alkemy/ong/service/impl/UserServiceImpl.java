package com.alkemy.ong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.alkemy.ong.models.entity.User;
import com.alkemy.ong.models.mapper.UserMapper;
import com.alkemy.ong.models.request.UserRequest;
import com.alkemy.ong.models.response.UserResponse;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;
import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;
   

    @Transactional
    @Override
    public void delete(Long id) throws UsernameNotFoundException {
        User user = userRepository.findById(id).orElseThrow();
        user.setSoftDelete(true);
        userRepository.save(user);
    }

    @Override
    public UserResponse update(Long id, UserRequest request) throws UsernameNotFoundException {
        User entity = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "the searched user does not exist"));     //error 404
        User entitySaved = userRepository.save(userMapper.userDtoEntity(entity, request));
        return userMapper.convertTo(entitySaved);
    }
}