package com.desertskyrangers.flightdeck.adapter.store;

import com.desertskyrangers.flightdeck.BaseTest;
import com.desertskyrangers.flightdeck.core.model.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PersistenceCascadeTest extends BaseTest {

	@Autowired
	private StatePersistingService statePersistingService;

	@Autowired
	private EntityManager entityManager;

	@Test
	void testUpsertTokenWithNewUser() {
		// given
		User user = new User().id( UUID.randomUUID() ).username( "newuser" ).email( "newuser@example.com" );
		UserToken token = new UserToken().id( UUID.randomUUID() ).principal( "newuser" ).credential( "password" ).user( user );

		// when
		statePersistingService.upsert( token );
		entityManager.flush();
		entityManager.clear();

		// then
		assertThat( stateRetrieving.findUser( user.id() ) ).isPresent();
		assertThat( stateRetrieving.findUserTokenByPrincipal( "newuser" ) ).isPresent();
	}

	@Test
	void testUpsertMemberWithNewUserAndGroup() {
		// given
		User user = new User().id( UUID.randomUUID() ).username( "memberuser" ).email( "memberuser@example.com" );
		Group group = new Group().id( UUID.randomUUID() ).name( "New Group" ).type( Group.Type.GROUP );
		Member member = new Member().id( UUID.randomUUID() ).user( user ).group( group ).status( Member.Status.ACCEPTED );

		// when
		statePersistingService.upsert( member );
		entityManager.flush();
		entityManager.clear();

		// then
		assertThat( stateRetrieving.findUser( user.id() ) ).isPresent();
		assertThat( stateRetrieving.findGroup( group.id() ) ).isPresent();
		assertThat( stateRetrieving.findMembership( member.id() ) ).isPresent();
	}

	@Test
	void testUpsertFlightWithNewPilotAndAircraft() {
		// given
		User pilot = new User().id( UUID.randomUUID() ).username( "pilot" ).email( "pilot@example.com" );
		Aircraft aircraft = new Aircraft()
			.id( UUID.randomUUID() )
			.name( "Test Aircraft" )
			.type( AircraftType.FIXEDWING )
			.owner( pilot.id() )
			.ownerType( OwnerType.USER );
		
		Flight flight = new Flight()
			.id( UUID.randomUUID() )
			.pilot( pilot )
			.aircraft( aircraft )
			.timestamp( System.currentTimeMillis() )
			.duration( 1000 );

		// when
		statePersistingService.upsert( flight );
		entityManager.flush();
		entityManager.clear();

		// then
		assertThat( stateRetrieving.findUser( pilot.id() ) ).isPresent();
		assertThat( stateRetrieving.findAircraft( aircraft.id() ) ).isPresent();
		assertThat( stateRetrieving.findFlight( flight.id() ) ).isPresent();
	}

	@Test
	void testUpsertLocationWithNewUser() {
		// given
		User user = new User().id( UUID.randomUUID() ).username( "locuser" ).email( "locuser@example.com" );
		Location location = new Location()
			.id( UUID.randomUUID() )
			.user( user )
			.name( "New Location" )
			.latitude( 40.0 )
			.longitude( -111.0 );

		// when
		statePersistingService.upsert( location );
		entityManager.flush();
		entityManager.clear();

		// then
		assertThat( stateRetrieving.findUser( user.id() ) ).isPresent();
		assertThat( stateRetrieving.findLocation( location.id() ) ).isPresent();
	}

	@Test
	void testUpsertUserWithNewToken() {
		// given
		User user = new User().id( UUID.randomUUID() ).username( "tokenuser" ).email( "tokenuser@example.com" );
		UserToken token = new UserToken().id( UUID.randomUUID() ).principal( "tokenuser" ).credential( "password" ).user( user );
		user.tokens( Set.of( token ) );

		// when
		statePersistingService.upsert( user );
		entityManager.flush();
		entityManager.clear();

		// then
		assertThat( stateRetrieving.findUser( user.id() ) ).isPresent();
		assertThat( stateRetrieving.findUserTokenByPrincipal( "tokenuser" ) ).isPresent();
	}

}
