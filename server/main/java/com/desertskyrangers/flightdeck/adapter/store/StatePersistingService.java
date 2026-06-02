package com.desertskyrangers.flightdeck.adapter.store;

import com.desertskyrangers.flightdeck.adapter.store.entity.*;
import com.desertskyrangers.flightdeck.adapter.store.entity.mapper.*;
import com.desertskyrangers.flightdeck.adapter.store.repo.*;
import com.desertskyrangers.flightdeck.core.model.*;
import com.desertskyrangers.flightdeck.port.StatePersisting;
import com.desertskyrangers.flightdeck.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatePersistingService implements StatePersisting {

	public static final String EMPTY_PROJECTION = "{}";

	private final AircraftRepo aircraftRepo;

	private final AwardRepo awardRepo;

	private final BatteryRepo batteryRepo;

	private final FlightRepo flightRepo;

	private final GroupRepo groupRepo;

	private final LocationRepo locationRepo;

	private final MemberRepo memberRepo;

	private final PreferencesRepo preferencesRepo;

	private final ProjectionRepo projectionRepo;

	private final TokenRepo tokenRepo;

	private final UserRepo userRepo;

	private final VerificationRepo verificationRepo;

	private final UserEntityMapper userMapper;

	private final GroupEntityMapper groupMapper;

	private final LocationEntityMapper locationMapper;

	private final MemberEntityMapper memberMapper;

	private final TokenEntityMapper tokenMapper;
	
	private final VerificationEntityMapper verificationMapper;

	private final BatteryEntityMapper batteryMapper;

	private final AircraftEntityMapper aircraftMapper;

	private final FlightEntityMapper flightMapper;

//	public StatePersistingService(
//		AircraftRepo aircraftRepo,
//		BatteryRepo batteryRepo,
//		FlightRepo flightRepo,
//		GroupRepo groupRepo,
//		LocationRepo locationRepo,
//		MemberRepo memberRepo,
//		PreferencesRepo preferencesRepo,
//		ProjectionRepo projectionRepo,
//		TokenRepo tokenRepo,
//		UserRepo userRepo,
//		VerificationRepo verificationRepo
//	) {
//		this.aircraftRepo = aircraftRepo;
//		this.batteryRepo = batteryRepo;
//		this.flightRepo = flightRepo;
//		this.groupRepo = groupRepo;
//		this.locationRepo = locationRepo;
//		this.memberRepo = memberRepo;
//		this.preferencesRepo = preferencesRepo;
//		this.projectionRepo = projectionRepo;
//		this.tokenRepo = tokenRepo;
//		this.userRepo = userRepo;
//		this.verificationRepo = verificationRepo;
//	}

	@Override
	public Aircraft upsert( Aircraft aircraft ) {
		return aircraftMapper.toAircraft( aircraftRepo.save( aircraftMapper.toEntity( aircraft ) ) );
	}

	public Award upsert(Award award ) {
		return AwardEntityMapper.INSTANCE.toAward( awardRepo.save( AwardEntityMapper.INSTANCE.toEntity( award ) ) );
	}
	@Override
	public void remove( Aircraft aircraft ) {
		aircraftRepo.deleteById( aircraft.id() );
	}

	@Override
	public Battery upsert( Battery battery ) {
		return batteryMapper.toBattery( batteryRepo.save( batteryMapper.toEntity( battery ) ) );
	}

	@Override
	public void remove( Battery battery ) {
		batteryRepo.deleteById( battery.id() );
	}

	@Override
	public Flight upsert( Flight flight ) {
		return flightMapper.toFlight( flightRepo.save( flightMapper.toEntity( flight ) ) );
	}

	@Override
	public void remove( Flight flight ) {
		flightRepo.deleteById( flight.id() );
	}

	@Override
	public void removeAllFlights() {
		flightRepo.deleteAll();
	}

	@Override
	public Group upsert( Group group ) {
		GroupEntity entity = groupMapper.toEntity( group );
		return groupMapper.toGroup( groupRepo.save( entity ) );
	}

	@Override
	public void remove( Group group ) {
		groupRepo.deleteById( group.id() );
	}

	@Override
	public void removeAllGroups() {
		groupRepo.deleteAll();
	}

	@Override
	public Location upsert( Location location ) {
		LocationEntity entity = locationMapper.toEntity( location );
		return locationMapper.toLocation( locationRepo.save( entity ) );
	}

	@Override
	public Location remove( Location location ) {
		locationRepo.deleteById( location.id() );
		return location;
	}

	@Override
	public void removeAllLocations() {
		locationRepo.deleteAll();
	}

	@Override
	public Member upsert( Member member ) {
		MemberEntity entity = memberMapper.toEntity( member );
		return memberMapper.toMember( memberRepo.save( entity ) );
	}

	@Override
	public Member remove( Member member ) {
		memberRepo.deleteById( member.id() );
		return member;
	}

	@Override
	public void removeAllMembers() {
		memberRepo.deleteAll();
	}

	@Override
	public Map<String, Object> upsertPreferences( User user, Map<String, Object> preferences ) {
		return Json.asMap( preferencesRepo.save( PreferencesEntity.from( user, preferences ) ).getJson() );
	}

	@Override
	public Map<String, Object> removePreferences( User user ) {
		Map<String, Object> preferences = Json.asMap( preferencesRepo.findById( user.id() ).orElse( new PreferencesProjection().setJson( "{}" ) ).getJson() );
		preferencesRepo.deleteById( user.id() );
		return preferences;
	}

	@Override
	public void upsert( UserToken token ) {
		tokenRepo.save( tokenMapper.toEntity( token ) );
	}

	@Override
	public User upsert( User user ) {
		User storedUser = userMapper.toUser( userRepo.save( userMapper.toEntity( user ) ) );
		userRepo.flush();
		return storedUser;
	}

	@Override
	public void remove( User user ) {
		userRepo.deleteById( user.id() );
		userRepo.flush();
	}

	@Override
	public Verification upsert( Verification verification ) {
		verificationRepo.save( verificationMapper.toEntity( verification ) );
		return verification;
	}

	@Override
	public Verification remove( Verification verification ) {
		verificationRepo.deleteById( verification.id() );
		return verification;
	}

	@Override
	public synchronized String upsertProjection( UUID id, String projection ) {
		return projectionRepo.save( new ProjectionEntity().setId( id ).setJson( projection ) ).getJson();
	}

	@Override
	public String removeProjections( UUID id ) {
		Optional<ProjectionEntity> entity = projectionRepo.findById( id );
		if( entity.isEmpty() ) return null;
		projectionRepo.deleteById( id );
		return entity.get().getJson();
	}

}
