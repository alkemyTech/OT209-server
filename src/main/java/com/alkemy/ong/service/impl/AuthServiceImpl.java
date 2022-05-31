package com.alkemy.ong.service.impl;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.alkemy.ong.auth.security.JwtTokenProvider;
import com.alkemy.ong.auth.utility.AuthenticationErrorEnum;
import com.alkemy.ong.auth.utility.RoleEnum;
import com.alkemy.ong.exception.EmailAlreadyExistException;
import com.alkemy.ong.models.entity.RoleEntity;
import com.alkemy.ong.models.entity.UserEntity;
import com.alkemy.ong.models.mapper.UserMapper;
import com.alkemy.ong.models.request.RegisterRequest;
import com.alkemy.ong.models.response.AuthenticateResponse;
import com.alkemy.ong.models.response.RegisterResponse;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public RegisterResponse register(RegisterRequest userRegister) {

		if (userRepository.findByEmail(userRegister.getEmail()) != null) {
			throw new EmailAlreadyExistException(userRegister.getEmail());
		}
		Set<RoleEntity> roleEntity = roleRepository.findByName(RoleEnum.ADMIN.getFullRoleName());

		if (roleEntity.isEmpty()) {
			throw new NullPointerException();
		}
		UserEntity userEntity = userMapper.toEntity(userRegister, roleEntity);
		userEntity = userRepository.save(userEntity);
		RegisterResponse registerResponse = userMapper.toUserRegisterResponde(userEntity);
		
		return registerResponse;
	}

	@Override
	public AuthenticateResponse login(String email, String password) throws Exception {
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(email, password));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			return new AuthenticateResponse(email, jwtTokenProvider.generateToken(authentication));
		} catch (Exception e) {
			return new AuthenticateResponse(AuthenticationErrorEnum.OK.name(), AuthenticationErrorEnum.FALSE.name());
		}
	}
}
