package com.desertskyrangers.flightdeck.adapter.store.entity.mapper;

import com.desertskyrangers.flightdeck.adapter.store.entity.FlightEntity;
import com.desertskyrangers.flightdeck.adapter.store.entity.UserEntity;
import com.desertskyrangers.flightdeck.adapter.store.repo.AircraftRepo;
import com.desertskyrangers.flightdeck.adapter.store.repo.BatteryRepo;
import com.desertskyrangers.flightdeck.adapter.store.repo.UserRepo;
import com.desertskyrangers.flightdeck.core.model.Flight;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class FlightEntityMapper {

	private final UserRepo userRepo;

	private final AircraftRepo aircraftRepo;

	private final BatteryRepo batteryRepo;

	private final UserEntityMapper userMapper;

	private final AircraftEntityMapper aircraftMapper;

	private final BatteryEntityMapper batteryMapper;

	public FlightEntityMapper( UserRepo userRepo, AircraftRepo aircraftRepo, BatteryRepo batteryRepo, UserEntityMapper userMapper, AircraftEntityMapper aircraftMapper, BatteryEntityMapper batteryMapper ) {
		this.userRepo = userRepo;
		this.aircraftRepo = aircraftRepo;
		this.batteryRepo = batteryRepo;
		this.userMapper = userMapper;
		this.aircraftMapper = aircraftMapper;
		this.batteryMapper = batteryMapper;
	}

	public FlightEntity toEntity( Flight flight ) {
		if( flight == null ) return null;

		FlightEntity entity = new FlightEntity();

		entity.setId( flight.id() );
		if( flight.pilot() != null ) {
			UserEntity pilotEntity = new UserEntity();
			pilotEntity.setId( flight.pilot().id() );
			pilotEntity.setUsername( flight.pilot().username() );
			pilotEntity.setEmail( flight.pilot().email() );
			entity.setPilot( pilotEntity );
		}
		entity.setUnlistedPilot( flight.unlistedPilot() );
		if( flight.observer() != null ) {
			UserEntity observerEntity = new UserEntity();
			observerEntity.setId( flight.observer().id() );
			observerEntity.setUsername( flight.observer().username() );
			observerEntity.setEmail( flight.observer().email() );
			entity.setObserver( observerEntity );
		}
		entity.setUnlistedObserver( flight.unlistedObserver() );
		if( flight.aircraft() != null ) {
			entity.setAircraft( aircraftMapper.toEntity( flight.aircraft() ) );
		}
		if( flight.batteries() != null ) {
			entity.setBatteries( flight.batteries().stream().map( batteryMapper::toEntity ).collect( Collectors.toSet() ) );
		}
		entity.setTimestamp( flight.timestamp() );
		entity.setDuration( flight.duration() );
		if( flight.location() != null ) entity.setLocationId( flight.location().id() );
		entity.setLatitude( flight.latitude() );
		entity.setLongitude( flight.longitude() );
		entity.setAltitude( flight.altitude() );
		entity.setNotes( flight.notes() );

		return entity;
	}

	public Flight toFlight( FlightEntity entity ) {
		if( entity == null ) return null;

		Flight flight = new Flight();

		flight.id( entity.getId() );
		if( entity.getPilot() != null ) flight.pilot( userMapper.toUser( entity.getPilot() ) );
		flight.unlistedPilot( entity.getUnlistedPilot() );
		if( entity.getObserver() != null ) flight.observer( userMapper.toUser( entity.getObserver() ) );
		flight.unlistedObserver( entity.getUnlistedObserver() );
		if( entity.getAircraft() != null ) flight.aircraft( aircraftMapper.toAircraft( entity.getAircraft() ) );
		if( entity.getBatteries() != null ) flight.batteries( entity.getBatteries().stream().map( batteryMapper::toBattery ).collect( Collectors.toSet() ) );
		flight.timestamp( entity.getTimestamp() );
		flight.duration( entity.getDuration() );
		flight.latitude( entity.getLatitude() );
		flight.longitude( entity.getLongitude() );
		flight.altitude( entity.getAltitude() );
		flight.notes( entity.getNotes() );

		return flight;
	}

}
