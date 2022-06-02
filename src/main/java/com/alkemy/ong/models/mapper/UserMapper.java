/*
TODO:Mappers
 */
package com.alkemy.ong.models.mapper;

import java.sql.Timestamp;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.alkemy.ong.models.entity.Role;
import com.alkemy.ong.models.entity.User;
import com.alkemy.ong.models.request.RegisterRequest;
import com.alkemy.ong.models.request.UserRequest;
import com.alkemy.ong.models.response.RegisterResponse;
import com.alkemy.ong.models.response.UserResponse;

@Component
public class UserMapper {

	@Autowired
	private PasswordEncoder encoder;

	public User toEntity(RegisterRequest userRequest, Set<Role> role) {
		User entity = new User();
		entity.setEmail(userRequest.getEmail());
		entity.setFirstName(userRequest.getFirstName());
		entity.setLastName(userRequest.getLastName());
		entity.setPassword(encoder.encode(userRequest.getPassword()));
		entity.setRol(role);
		entity.setSoftDelete(false);
		entity.setTimestamp(new Timestamp(System.currentTimeMillis()));
		return entity;
	}
	
	public RegisterResponse toUserRegisterResponde(User userEntity) {
		RegisterResponse registerResponse = new RegisterResponse();
		registerResponse.setId(userEntity.getId());
		registerResponse.setEmail(userEntity.getEmail());
		registerResponse.setFirstName(userEntity.getFirstName());
		registerResponse.setLastName(userEntity.getLastName());
		return registerResponse;
	}
        
        public User  userDtoEntity(User user, UserRequest request){
            user.setEmail(request.getEmail());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setPassword(encoder.encode(request.getPassword()));
            user.setPhoto(request.getPhoto());
           return user;
        }
        public UserResponse convertTo(User user){
            return UserResponse.builder()
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .photo(user.getPhoto())                    
                    .build();
        }
                
                
    
}
