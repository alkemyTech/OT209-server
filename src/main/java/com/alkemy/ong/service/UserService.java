package com.alkemy.ong.service;

import com.alkemy.ong.models.entity.UserEntity;
import com.alkemy.ong.models.request.UserRequest;
import com.alkemy.ong.models.response.UserResponse;
import java.util.List;

public interface UserService {

    void delete(Long id);

    public UserResponse update(Long id, UserRequest request);

    public List<UserResponse> getUsers();

    public boolean ExistsUserById(Long id);

    public UserEntity findById(Long id);
}
