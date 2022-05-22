/*
implementacion
 */
package com.alkemy.ong.service.impl;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.alkemy.ong.models.entity.User;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.security.token.ConfirmationToken;
import com.alkemy.ong.security.token.ConfirmationTokenRepository;
import com.alkemy.ong.security.token.ConfirmationTokenService;
import com.alkemy.ong.service.UserService;
import com.alkemy.ong.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService,UserDetailsService, Serializable {
        private static final long serialVersionUID = 1L;
    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final UserRepository appUserRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    @Transactional
    public String signUpUser(User appUser) {
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

//        TODO: SEND EMAIL

        return token;
    }
    @Transactional
    public void PutsignUpUser(User appUser) {
        boolean userExists = appUserRepository
                .findById(appUser.getId())
                .isPresent();

        if (userExists) {
            appUserRepository.save(appUser);
            log.info("Datos de usuario modificado");
        }else
        {
            throw new IllegalStateException("usuario con id "+ appUser.getId()+" no existe");
        }
    }
    public void delete(Long id){
        try{
            confirmationTokenRepository.deleteById(id);
            appUserRepository.deleteById(id);
            log.info("Usuario borrado");

        }catch(Exception err){
            log.info("Error durante el borrado de usuario");
        }

    }
    @Transactional
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
