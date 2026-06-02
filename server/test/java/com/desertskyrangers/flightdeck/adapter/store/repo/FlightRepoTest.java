package com.desertskyrangers.flightdeck.adapter.store.repo;

import com.desertskyrangers.flightdeck.BaseTest;
import com.desertskyrangers.flightdeck.adapter.store.entity.BatteryEntity;
import com.desertskyrangers.flightdeck.adapter.store.entity.FlightEntity;
import com.desertskyrangers.flightdeck.adapter.store.entity.mapper.BatteryEntityMapper;
import com.desertskyrangers.flightdeck.adapter.store.entity.mapper.FlightEntityMapper;
import com.desertskyrangers.flightdeck.core.model.Battery;
import com.desertskyrangers.flightdeck.core.model.User;
import com.desertskyrangers.flightdeck.port.StatePersisting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class FlightRepoTest extends BaseTest {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private FlightRepo flightRepo;

	@Autowired
	private StatePersisting statePersisting;

	@Autowired
	private BatteryEntityMapper batteryEntityMapper;

	@Autowired
	private FlightEntityMapper flightEntityMapper;

	@Test
	void testFindWithPageable() {
		// given
		User pilot = statePersisting.upsert( createTestUser( "otto", "otto@exemple.com" ) );
		flightRepo.save( createTestFlightEntity( pilot ) );
		flightRepo.save( createTestFlightEntity( pilot ) );
		flightRepo.save( createTestFlightEntity( pilot ) );
		flightRepo.save( createTestFlightEntity( pilot ) );
		flightRepo.save( createTestFlightEntity( pilot ) );

		// when
		Page<FlightEntity> actual = flightRepo.findAll( PageRequest.of( 0, 3, Sort.Direction.DESC, "timestamp" ) );

		// then
		assertThat( actual ).isNotNull();
		assertThat( actual.getSize() ).isEqualTo( 3 );
		assertThat( actual.getTotalElements() ).isEqualTo( 5 );
	}

	@Test
	void testCountByBattery() {
		// given
		User pilot = statePersisting.upsert( createTestUser( "Quinn", "quinn@exemple.com" ) );
		Battery battery = statePersisting.upsert( createTestBattery( pilot ) );
		flightRepo.save( createTestFlightEntity( pilot ).setBatteries( Set.of( batteryEntityMapper.toEntity( battery ) ) ) );
		flightRepo.save( createTestFlightEntity( pilot ).setBatteries( Set.of( batteryEntityMapper.toEntity( battery ) ) ) );

		// when
		int count = flightRepo.countByBattery( batteryEntityMapper.toEntity( battery ) );

		// then
		assertThat( count ).isEqualTo( 2 );
	}

	@Test
	void testGetFlightTimeByBattery() {
		// given
		User pilot = statePersisting.upsert( createTestUser( "roger", "roger@exemple.com" ) );
		Battery battery = statePersisting.upsert( createTestBattery( pilot ) );
		flightRepo.save( createTestFlightEntity( pilot ).setDuration( 45 ).setBatteries( Set.of( batteryEntityMapper.toEntity( battery ) ) ) );
		flightRepo.save( createTestFlightEntity( pilot ).setDuration( 25 ).setBatteries( Set.of( batteryEntityMapper.toEntity( battery ) ) ) );

		// when
		long time = flightRepo.getFlightTimeByBattery( batteryEntityMapper.toEntity( battery ) );

		// then
		assertThat( time ).isEqualTo( 70 );
	}

	private FlightEntity createTestFlightEntity( User pilot ) {
		return flightEntityMapper.toEntity( createTestFlight( pilot ) );
	}

}
