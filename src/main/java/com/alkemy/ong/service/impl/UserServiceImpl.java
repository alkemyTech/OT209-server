/*
implementacion
 */
package com.alkemy.ong.service.impl;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.alkemy.ong.auth.utility.AuthenticationErrorEnum;
import com.alkemy.ong.auth.utility.RoleEnum;
import com.alkemy.ong.exception.EmailAlreadyExistException;
import com.alkemy.ong.models.entity.Role;
import com.alkemy.ong.models.entity.User;
import com.alkemy.ong.models.mapper.UserMapper;
import com.alkemy.ong.models.request.RegisterRequest;
import com.alkemy.ong.models.response.AuthenticateResponse;
import com.alkemy.ong.models.response.RegisterResponse;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;


import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Personalization;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired 
	private AuthenticationManager authenticationManager;
	
	@Override
	public RegisterResponse register(RegisterRequest userRegister) {
		if (userRepository.findByEmail(userRegister.getEmail()) != null) {
			throw new EmailAlreadyExistException(userRegister.getEmail());
		}
		
		Set<Role> roleEntity = roleRepository.findByName(RoleEnum.USER.getFullRoleName());
		
		User userEntity = userMapper.toEntity(userRegister, roleEntity);
		
		userEntity = userRepository.save(userEntity);
		RegisterResponse registerResponse = userMapper.toUserRegisterResponde(userEntity);
		
		return registerResponse;
	}

	@Override
	public AuthenticateResponse login(String email, String password) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(email, password));

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}catch(Exception e) {
			return new AuthenticateResponse(AuthenticationErrorEnum.OK.name(),AuthenticationErrorEnum.FALSE.name());
		}
		return new AuthenticateResponse(email,password);

	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User entity = userRepository.findByEmail(email);
	    if (entity == null) {
	      throw new UsernameNotFoundException("User not found");
	    }
	    return new org.springframework.security.core.userdetails.User(entity.getEmail(),entity.getPassword(),mappRoles(entity.getRol()));
	}
	
	private Collection<? extends GrantedAuthority> mappRoles(Set<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
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
			logger.info(response.getBody());
			return response.getBody();
		} catch (IOException ex) {
			throw ex;
		}
	}


	// This class handel the dynamic data for the template
	// Feel free to customise this class our to putted on other file
	private static class DynamicTemplatePersonalization extends Personalization {

		@JsonProperty(value = "dynamic_template_data")
		private Map<String, String> dynamic_template_data;

		@JsonProperty("dynamic_template_data")
		public Map<String, String> getDynamicTemplateData() {
			if (dynamic_template_data == null) {
				return Collections.<String, String>emptyMap();
			}
			return dynamic_template_data;
		}

		public void addDynamicTemplateData(String key, String value) {
			if (dynamic_template_data == null) {
				dynamic_template_data = new HashMap<String, String>();
				dynamic_template_data.put(key, value);
			} else {
				dynamic_template_data.put(key, value);
			}
		}

	}
	
}
