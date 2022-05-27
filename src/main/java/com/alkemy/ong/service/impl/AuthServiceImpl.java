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
import com.alkemy.ong.service.AuthService;

import javax.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {

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
        } catch (Exception e) {
            return new AuthenticateResponse(AuthenticationErrorEnum.OK.name(), AuthenticationErrorEnum.FALSE.name());
        }
        return new AuthenticateResponse(email, password);

    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User entity = userRepository.findByEmail(email);
        if (entity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(entity.getEmail(), entity.getPassword(), mappRoles(entity.getRol()));
    }

    private Collection<? extends GrantedAuthority> mappRoles(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

  
}
