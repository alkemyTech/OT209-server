package com.alkemy.ong.service.impl;

import java.io.IOException;
import java.util.Set;

import com.alkemy.ong.models.response.UserResponse;
import com.sendgrid.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
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



		if (userRepository.existsByEmail(userRegister.getEmail())) { //@Adrián Fernández: Change findbyEmail to exists
			throw new EmailAlreadyExistException(userRegister.getEmail());
		}
		Set<RoleEntity> roleEntity = roleRepository.findByName(RoleEnum.ADMIN.getFullRoleName());

		if (roleEntity.isEmpty()) {
			throw new NullPointerException();
		}
		UserEntity userEntity = userMapper.toEntity(userRegister, roleEntity);
		userEntity = userRepository.save(userEntity);
		RegisterResponse registerResponse = userMapper.toUserRegisterResponde(userEntity,
				jwtTokenProvider.generateToken(authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(userRegister.getEmail(), userRegister.getPassword()))));
		
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

	@Override
	@Transactional(readOnly = true)
	public UserResponse userAuth(String token) {
		token = token.replace("Bearer ", "");
		String email = jwtTokenProvider.getJWTUsername(token);

		UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"the searched user does not exist"));
		return userMapper.convertTo(userEntity);



	}
	public String sendTextEmail() throws IOException {
		/*
		 * The sender email should be the same as we used to Create a Single Sender
		 * Verification
		 */
		log.info("entrando a sendTextEmail");
		Email from = new Email("mdlprofesional@gmail.com");
		String subject = "Maximiliano";
		Email to = new Email("mdlprofesional@gmail.com");
		Content content = new Content("text/plain", "This is a test");
		Mail mail = new Mail(from, subject, to, content);

		log.info("antes");
		SendGrid sg = new SendGrid("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");
		log.info("despues");
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			log.info(response.getBody());
			return response.getBody();
		} catch (IOException ex) {
			throw ex;
		}
	}
}
