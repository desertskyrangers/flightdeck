package com.desertskyrangers.flightdeck.adapter.web;

import com.desertskyrangers.flightdeck.adapter.web.jwt.JwtFilter;
import com.desertskyrangers.flightdeck.adapter.web.jwt.JwtTokenProvider;
import com.desertskyrangers.flightdeck.core.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebBaseSecurity {

	private final AppUserDetailsService appUserDetailsService;

	private final JwtTokenProvider jwtTokenProvider;

	public WebBaseSecurity( AppUserDetailsService appUserDetailsService, JwtTokenProvider jwtTokenProvider ) {
		this.appUserDetailsService = appUserDetailsService;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsServiceBean() {
		return appUserDetailsService;
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider( appUserDetailsService );
		authenticationProvider.setPasswordEncoder( passwordEncoder() );
		return authenticationProvider;
	}

	@Bean
	public SecurityFilterChain configure( HttpSecurity http ) throws Exception {
		// @formatter:off
		return http
			.cors( cors -> cors.configurationSource( corsConfigurationSource() ) )
			.csrf( AbstractHttpConfigurer::disable )
			.authorizeHttpRequests( requests -> requests
				.requestMatchers( PathPatternRequestMatcher.pathPattern(HttpMethod.POST, ApiPath.AUTH_RECOVER )).permitAll()
				.requestMatchers( PathPatternRequestMatcher.pathPattern(HttpMethod.POST, ApiPath.AUTH_REGISTER) ).permitAll()
				.requestMatchers( PathPatternRequestMatcher.pathPattern(HttpMethod.POST, ApiPath.AUTH_RESEND) ).permitAll()
				.requestMatchers( PathPatternRequestMatcher.pathPattern(HttpMethod.POST, ApiPath.AUTH_RESET) ).permitAll()
				.requestMatchers( PathPatternRequestMatcher.pathPattern(HttpMethod.POST, ApiPath.AUTH_VERIFY) ).permitAll()
				.requestMatchers( PathPatternRequestMatcher.pathPattern(HttpMethod.POST, ApiPath.AUTH_LOGIN )).permitAll()
				.requestMatchers( PathPatternRequestMatcher.pathPattern(HttpMethod.POST, ApiPath.AUTH_LOGOUT) ).permitAll()
				.requestMatchers( PathPatternRequestMatcher.pathPattern(HttpMethod.GET, ApiPath.DASHBOARD + "/**") ).permitAll()
				.requestMatchers( PathPatternRequestMatcher.pathPattern(HttpMethod.GET, ApiPath.MONITOR_STATUS) ).permitAll()
				.anyRequest().authenticated()
			)
			.addFilterAfter( new JwtFilter( jwtTokenProvider ), UsernamePasswordAuthenticationFilter.class )
			.build();
		// @formatter:on
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins( List.of( "*" ) );
		configuration.setAllowedMethods( List.of( "GET", "POST", "PUT", "DELETE", "OPTIONS" ) );
		configuration.setAllowedHeaders( List.of( "*" ) );
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration( "/**", configuration );
		return source;
	}

}
