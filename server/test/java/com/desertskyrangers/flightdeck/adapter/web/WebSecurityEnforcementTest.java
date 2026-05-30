package com.desertskyrangers.flightdeck.adapter.web;

import com.desertskyrangers.flightdeck.adapter.web.jwt.JwtToken;
import com.desertskyrangers.flightdeck.adapter.web.jwt.JwtTokenProvider;
import com.desertskyrangers.flightdeck.core.model.User;
import com.desertskyrangers.flightdeck.util.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest( webEnvironment = RANDOM_PORT )
@AutoConfigureTestRestTemplate
public class WebSecurityEnforcementTest {

	private TestRestTemplate restTemplate;

	private URL url;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@LocalServerPort
	int port;

	@BeforeEach
	public void setUp() throws MalformedURLException {
		restTemplate = new TestRestTemplate( "user", "password" );
		url = URI.create( "http://localhost:" + port + ApiPath.PROFILE ).toURL();
	}

	@Test
	@SuppressWarnings("unchecked")
	public void whenUserWithValidJwtRequestsProfilePage_ThenSuccess() {
		// given
		Authentication authentication = new TestingAuthenticationToken( "testuser", "password", "USER" );
		String jwtToken = tokenProvider.createToken( new User(), authentication, false );

		HttpHeaders headers = new HttpHeaders();
		headers.add( JwtToken.AUTHORIZATION_HEADER, JwtToken.AUTHORIZATION_TYPE + " " + jwtToken );
		HttpEntity<String> entity = new HttpEntity<>( "parameters", headers );

		// when
		ResponseEntity<String> response = restTemplate.exchange( url.toString(), HttpMethod.GET, entity, String.class );

		// then
		Map<String,Object> map = Json.asMap( response.getBody() );
		Map<String,Object> account =  (Map<String,Object>)map.get( "account" ) ;

		assertThat( response.getStatusCode() ).isEqualTo( HttpStatus.BAD_REQUEST );
		for( String key : account.keySet() ) {
			if( key.equals("emailVerified")) {
				assertThat( (Boolean)account.get( key ) ).isFalse();
			} else if( key.equals("smsVerified") ) {
				assertThat( (Boolean)account.get( key ) ).isFalse();
			} else {
				assertThat( account.get( key ) ).isNull();
			}
		}
	}

	@Test
	public void whenUserWithInvalidJwtRequestsProfilePage_ThenForbidden() {
		String jwtToken = "fake.jwt.token";
		HttpHeaders headers = new HttpHeaders();
		headers.add( JwtToken.AUTHORIZATION_HEADER, JwtToken.AUTHORIZATION_TYPE + " " + jwtToken );
		HttpEntity<String> entity = new HttpEntity<>( "parameters", headers );

		ResponseEntity<String> response = restTemplate.exchange( url.toString(), HttpMethod.GET, entity, String.class );

		assertThat( response.getStatusCode() ).isEqualTo( HttpStatus.FORBIDDEN );
		assertThat( response.getBody() ).isNull();
	}

}
