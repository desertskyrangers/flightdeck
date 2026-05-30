package com.desertskyrangers.flightdeck.core;

import com.desertskyrangers.flightdeck.BaseTest;
import com.desertskyrangers.flightdeck.core.model.User;
import com.desertskyrangers.flightdeck.core.model.UserToken;
import com.desertskyrangers.flightdeck.port.StateRetrieving;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class AppUserDetailsServiceTest extends BaseTest {

	@MockitoBean
	StateRetrieving mockStateRetrieving;

	@Autowired
	private AppUserDetailsService appUserDetailsService;

	@Test
	void testLoadUserByUsername() {
		// given
		String username = "testuser";
		String password = "password";
		UserToken credential = new UserToken().principal( username ).credential( password );

		User user = new User();
		credential.user( user );
		user.tokens( Set.of( credential ) );

		when( mockStateRetrieving.findUserTokenByPrincipal( username ) ).thenReturn( Optional.of( credential ) );

		// when
		UserDetails details = appUserDetailsService.loadUserByUsername( username );

		// then
		assertThat( details ).isInstanceOf( AppTokenDetails.class );
		AppTokenDetails principal = (AppTokenDetails)details;
		assertThat( principal.getUsername() ).isEqualTo( username );
		assertThat( principal.getPassword() ).isEqualTo( password );
		assertThat( principal.getAuthorities() ).containsExactlyInAnyOrder( AppRole.USER );
	}

}
