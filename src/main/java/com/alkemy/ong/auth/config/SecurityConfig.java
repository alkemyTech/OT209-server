package com.alkemy.ong.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.alkemy.ong.auth.filter.JwtAuthenticationFilter;
import com.alkemy.ong.auth.service.CustomUserDetailsService;
import com.alkemy.ong.auth.utility.RoleEnum;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

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
        managerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    private static final String[] publicEndpoint = {
        "/swagger-resources/**",
        "/swagger-ui/**", "/v2/api-docs",
        "/v3/api-docs",
        "/api/docs",
        "/api/docs/**",
        "/api/docs/swagger-ui",
        "/swagger-ui.html",
        "/**/swagger-ui/**",
        "/swagger-ui"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors()
                .and()   
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //auth
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/register").permitAll()
                //organization
                .antMatchers(HttpMethod.GET, "/organization/public").permitAll()
                .antMatchers(HttpMethod.PUT, "/organization/public").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.POST, "/organization/create").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                //ong
                .antMatchers(HttpMethod.POST, "/ong/activities").permitAll()
                //users
                .antMatchers(HttpMethod.DELETE, "/users/{id}").permitAll()
                .antMatchers(HttpMethod.PATCH, "/users/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/users").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                //categiries
                .antMatchers(HttpMethod.GET, "/categories").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.POST, "/categories").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.PUT, "/categories/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.DELETE, "/categories/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                //news
                .antMatchers(HttpMethod.GET, "/news").permitAll()
                .antMatchers(HttpMethod.GET, "/news/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.POST, "/news").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.PUT, "/news/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.DELETE, "/news/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                //Testimonials
                .antMatchers(HttpMethod.POST, "/testimonials").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.PUT, "/testimonials/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.DELETE, "/testimonials/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                //activities
                .antMatchers(HttpMethod.PUT, "/ong/activities/{id}").hasRole((RoleEnum.ADMIN.getSimpleRoleName()))
                //commnets
                .antMatchers(HttpMethod.GET, "/comments").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                //members
                .antMatchers(HttpMethod.POST, "/members/create").hasRole((RoleEnum.ADMIN.getSimpleRoleName()))
                .antMatchers(HttpMethod.GET, "/members/list").hasRole((RoleEnum.ADMIN.getSimpleRoleName()))
                .antMatchers(HttpMethod.PUT, "/members/update/{id}").hasRole((RoleEnum.ADMIN.getSimpleRoleName()))
                .antMatchers(HttpMethod.DELETE, "/members/delete/{id}").hasRole((RoleEnum.ADMIN.getSimpleRoleName()))
                //contacts
                .antMatchers(HttpMethod.GET, "/contacts").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.POST, "/contacts").permitAll()
                // comments
                .antMatchers(HttpMethod.POST, "/comments").permitAll()
                //slides
                .antMatchers(HttpMethod.POST, "/Slides").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.PUT, "/Slides/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.DELETE, "/Slides/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.GET, "/Slides").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(HttpMethod.GET, "/Slides/{id}").hasRole(RoleEnum.ADMIN.getSimpleRoleName())
                .antMatchers(publicEndpoint).permitAll()
                /*agregar autorizaciones a los endpoints pendientes en desarrollo

	         *EJEMPLO:
	         * PARA TODOS:
	         * .antMatchers(HttpMethod.<TIPO>, "<endpoint>").permitAll()
	         * PARA USER:
	         * .antMatchers(HttpMethod.<TIPO>, "<endpoint>").hasRole(RoleEnum.USER.getSimpleRoleName);
	         * PARA ADMIN:
	         * .antMatchers(HttpMethod.<TIPO>, "<endpoint>").hasRole(RoleEnum.ADMIN.getSimpleRoleName);
                 */
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint());
 
           }

}
