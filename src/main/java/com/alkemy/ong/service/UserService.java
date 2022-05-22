/*
 interface service
 */
package com.alkemy.ong.service;


import com.alkemy.ong.models.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService  {
    public UserDetails loadUserByUsername(String email);
    public String signUpUser(User appUser);
    public void PutsignUpUser(User appUser);
    public void delete(Long id);
    public int enableAppUser(String email);
}
