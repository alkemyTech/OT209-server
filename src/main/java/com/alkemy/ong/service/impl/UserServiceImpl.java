package com.alkemy.ong.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.alkemy.ong.models.entity.UserEntity;
import com.alkemy.ong.models.mapper.UserMapper;
import com.alkemy.ong.models.request.UserRequest;
import com.alkemy.ong.models.response.UserResponse;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;
import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public void delete(Long id) throws UsernameNotFoundException {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "the searched user does not exist"));
        user.setSoftDelete(true);
        userRepository.save(user);
    }

    @Override
    public UserResponse update(Long id, UserRequest request) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "the searched user does not exist"));     //error 404
        UserEntity entitySaved = userRepository.save(userMapper.userDtoEntity(entity, request));
        return userMapper.convertTo(entitySaved);
    }

    @Override
    public List<UserResponse> getUsers() {
        List<UserEntity> usersEntities = userRepository.findAll();

        return userMapper.userEntityDtoList(usersEntities);
    }

    @Override
    public boolean ExistsUserById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "the searched user does not exist"));
    }
}
