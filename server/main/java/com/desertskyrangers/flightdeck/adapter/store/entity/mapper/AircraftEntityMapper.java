package com.desertskyrangers.flightdeck.adapter.store.entity.mapper;

import com.desertskyrangers.flightdeck.adapter.store.entity.AircraftEntity;
import com.desertskyrangers.flightdeck.adapter.store.repo.AircraftRepo;
import com.desertskyrangers.flightdeck.core.model.Aircraft;
import com.desertskyrangers.flightdeck.core.model.AircraftType;
import com.desertskyrangers.flightdeck.core.model.OwnerType;
import org.springframework.stereotype.Component;

@Component
public class AircraftEntityMapper {

	private final AircraftRepo aircraftRepo;

	public AircraftEntityMapper( AircraftRepo aircraftRepo ) {
		this.aircraftRepo = aircraftRepo;
	}

	public AircraftEntity toEntity( Aircraft aircraft ) {
		if( aircraft == null ) return null;

		AircraftEntity entity = aircraftRepo.findById( aircraft.id() ).orElse( new AircraftEntity() );

		entity.setId( aircraft.id() );
		entity.setName( aircraft.name() );
		if( aircraft.type() != null ) entity.setType( aircraft.type().name().toLowerCase() );
		entity.setMake( aircraft.make() );
		entity.setModel( aircraft.model() );
		if( aircraft.status() != null ) entity.setStatus( aircraft.status().name().toLowerCase() );
		entity.setNightLights( aircraft.nightLights() );

		entity.setWingspan( aircraft.wingspan() );
		entity.setLength( aircraft.length() );
		entity.setWingarea( aircraft.wingarea() );
		entity.setWeight( aircraft.weight() );

		entity.setFlightCount( aircraft.flightCount() );
		entity.setFlightTime( aircraft.flightTime() );

		entity.setOwner( aircraft.owner() );
		if( aircraft.ownerType() != null ) entity.setOwnerType( aircraft.ownerType().name().toLowerCase() );

		entity.setBaseColor( aircraft.baseColor() );
		entity.setTrimColor( aircraft.trimColor() );

		return entity;
	}

	public Aircraft toAircraft( AircraftEntity entity ) {
		if( entity == null ) return null;

		Aircraft aircraft = new Aircraft();

		aircraft.id( entity.getId() );
		aircraft.name( entity.getName() );
		if( entity.getType() != null ) aircraft.type( AircraftType.valueOf( entity.getType().toUpperCase() ) );
		aircraft.make( entity.getMake() );
		aircraft.model( entity.getModel() );
		if( entity.getStatus() != null ) aircraft.status( Aircraft.Status.valueOf( entity.getStatus().toUpperCase() ) );
		aircraft.nightLights( entity.getNightLights() != null && entity.getNightLights() );

		aircraft.wingspan( entity.getWingspan() == null ? 0.0 : entity.getWingspan() );
		aircraft.length( entity.getLength() == null ? 0.0 : entity.getLength() );
		aircraft.wingarea( entity.getWingarea() == null ? 0.0 : entity.getWingarea() );
		aircraft.weight( entity.getWeight() == null ? 0.0 : entity.getWeight() );

		aircraft.flightCount( entity.getFlightCount() == null ? 0 : entity.getFlightCount() );
		aircraft.flightTime( entity.getFlightTime() == null ? 0 : entity.getFlightTime() );

		aircraft.owner( entity.getOwner() );
		if( entity.getOwnerType() != null ) aircraft.ownerType( OwnerType.valueOf( entity.getOwnerType().toUpperCase() ) );

		aircraft.baseColor( entity.getBaseColor() );
		aircraft.trimColor( entity.getTrimColor() );

		return aircraft;
	}

}
