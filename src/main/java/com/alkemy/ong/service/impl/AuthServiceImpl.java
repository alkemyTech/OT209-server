package com.alkemy.ong.service.impl;

import java.io.IOException;
import java.util.Set;

import com.alkemy.ong.models.response.UserResponse;
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
import com.alkemy.ong.service.EmailService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

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
        if (userRepository.existsByEmail(userRegister.getEmail())) { // @Adrián Fernández: Change findbyEmail to exists
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
        try {
            this.emailService.sendTemplateSolosMas(registerResponse.getEmail());
        } catch (IOException e) {
            System.out.println("ERROR WHILE SENDING EMAIL TEMPLATE. CHECK EMAIL IMPLEMENTATION");
        }
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

        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the searched user does not exist"));
        return userMapper.convertTo(userEntity);
    }

    public String getRol(String token) {

        return null;
    }

}
