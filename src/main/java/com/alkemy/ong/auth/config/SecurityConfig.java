/*
Configuración de security
 */
package com.alkemy.ong.auth.config;

import com.alkemy.ong.auth.filter.JwtRequestFilters;
import com.alkemy.ong.auth.service.OngUserDetailsService;
import com.alkemy.ong.auth.utility.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private OngUserDetailsService ongUserDetailsService;
	@Autowired
	private JwtRequestFilters filters;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
		managerBuilder.userDetailsService(ongUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http.csrf()
	        .disable()
	        .cors()
	        .and()
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .authorizeRequests()
	        .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
	        .antMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .antMatchers(HttpMethod.GET, "/organization/public").permitAll()
                .antMatchers(HttpMethod.DELETE, "/user/{id}").permitAll()
                .antMatchers(HttpMethod.PATCH, "/user/{id}").permitAll()
				.antMatchers(HttpMethod.GET, "/news/*").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
				.antMatchers(HttpMethod.POST, "/news").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
				.antMatchers(HttpMethod.PUT, "/news/*").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
				.antMatchers(HttpMethod.DELETE, "/news/*").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
	        /*agregar autorizaciones a los endpoints pendientes en desarrollo
	         *EJEMPLO:
	         * PARA TODOS:
	         * .antMatchers(HttpMethod.<TIPO>, "<endpoint>").permitAll()
	         * PARA USER:
	         * .antMatchers(HttpMethod.<TIPO>, "<endpoint>").hasRole(RoleEnum.USER.getSimpleRoleName);
	         * PARA ADMIN:
	         * .antMatchers(HttpMethod.<TIPO>, "<endpoint>").hasRole(RoleEnum.ADMIN.getSimpleRoleName);
            */
	        .anyRequest().authenticated()
	        .and()
	        .httpBasic();
		http.addFilterBefore(filters, UsernamePasswordAuthenticationFilter.class);
	    /*                Agregar restriccion para categories
        .antMatchers(HttpMethod.GET, "/categories").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
        .antMatchers(HttpMethod.POST, "/categories").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
        .antMatchers(HttpMethod.PUT, "/categories/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
        .antMatchers(HttpMethod.DELETE, "/categories/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
	     */

	  }

}
