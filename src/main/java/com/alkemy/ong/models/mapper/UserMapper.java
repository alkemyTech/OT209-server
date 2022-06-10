/*
TODO:Mappers
 */
package com.alkemy.ong.models.mapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.alkemy.ong.models.entity.RoleEntity;
import com.alkemy.ong.models.entity.UserEntity;
import com.alkemy.ong.models.request.RegisterRequest;
import com.alkemy.ong.models.request.UserRequest;
import com.alkemy.ong.models.response.RegisterResponse;
import com.alkemy.ong.models.response.UserResponse;

@Component
public class UserMapper {

	@Autowired
	private PasswordEncoder encoder;

	public UserEntity toEntity(RegisterRequest userRequest, Set<RoleEntity> role) {
		UserEntity entity = new UserEntity();
		entity.setEmail(userRequest.getEmail());
		entity.setFirstName(userRequest.getFirstName());
		entity.setLastName(userRequest.getLastName());
		entity.setPassword(encoder.encode(userRequest.getPassword()));
		entity.setRol(role);
		entity.setSoftDelete(false);
		entity.setTimestamp(new Timestamp(System.currentTimeMillis()));
		return entity;
	}
	
	public RegisterResponse toUserRegisterResponde(UserEntity userEntity, String token) {
		RegisterResponse registerResponse = new RegisterResponse();
		registerResponse.setId(userEntity.getId());
		registerResponse.setEmail(userEntity.getEmail());
		registerResponse.setFirstName(userEntity.getFirstName());
		registerResponse.setLastName(userEntity.getLastName());
		registerResponse.setToken(token);
		return registerResponse;
	}
        
        public UserEntity  userDtoEntity(UserEntity user, UserRequest request){
            user.setEmail(request.getEmail());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setPassword(encoder.encode(request.getPassword()));
            user.setPhoto(request.getPhoto());
           return user;
        }
        public UserResponse convertTo(UserEntity user){
            return UserResponse.builder()
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .photo(user.getPhoto())
					.id(user.getId())
                    .build();
        }

		public UserResponse convertToAll(UserEntity user){
			return UserResponse.builder()
					.id(user.getId())
					.email(user.getEmail())
					.firstName(user.getFirstName())
					.lastName(user.getLastName())
					.photo(user.getPhoto())
					.build();
		}

		public List<UserResponse> userEntityDtoList(List<UserEntity> entities){

			return entities.stream().map(this::convertToAll).collect(Collectors.toList());
		}
    
}
