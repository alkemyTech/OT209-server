package com.alkemy.ong.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alkemy.ong.auth.security.JwtTokenProvider;
import com.alkemy.ong.auth.service.CustomUserDetailsService;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final String AUTHORIZACION_FOR_HEADER = "Authorization";
	private static final String START_WITH_BEARER = "Bearer";
	private static final int BEARER_PART_LENGHT = 7;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Get token from request
		String token = getJWTFromRequest(request);

		// validate token
		if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
			//get username from token
			String username = jwtTokenProvider.getJWTUsername(token);

			//load userdetails with info from token
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			//set security
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		filterChain.doFilter(request, response);
	}

	
	private String getJWTFromRequest(HttpServletRequest request) {
		// From postman authorization header
		String bearerToken = request.getHeader(AUTHORIZACION_FOR_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(START_WITH_BEARER)) {
			return bearerToken.substring(BEARER_PART_LENGHT, bearerToken.length());
		}
		return null;
	}

}
