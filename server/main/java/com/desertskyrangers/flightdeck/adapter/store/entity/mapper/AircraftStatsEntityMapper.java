package com.desertskyrangers.flightdeck.adapter.store.entity.mapper;

import com.desertskyrangers.flightdeck.adapter.store.entity.AircraftStatsEntity;
import com.desertskyrangers.flightdeck.core.model.AircraftStats;
import com.desertskyrangers.flightdeck.core.model.AircraftType;
import org.springframework.stereotype.Component;

@Component
public class AircraftStatsEntityMapper {

	public AircraftStatsEntity toEntity( AircraftStats stats ) {
		if( stats == null ) return null;

		AircraftStatsEntity entity = new AircraftStatsEntity();

		entity.setId( stats.id() );
		entity.setName( stats.name() );
		if( stats.type() != null ) entity.setType( stats.type().name().toLowerCase() );
		entity.setLastFlightTimestamp( stats.lastFlightTimestamp() );
		entity.setFlightCount( stats.flightCount() );
		entity.setFlightTime( stats.flightTime() );

		return entity;
	}

	public AircraftStats toAircraftStats( AircraftStatsEntity entity ) {
		if( entity == null ) return null;

		AircraftStats stats = new AircraftStats();

		stats.id( entity.getId() );
		stats.name( entity.getName() );
		if( entity.getType() != null ) stats.type( AircraftType.valueOf( entity.getType().toUpperCase() ) );
		stats.lastFlightTimestamp( entity.getLastFlightTimestamp() );
		stats.flightCount( entity.getFlightCount() );
		stats.flightTime( entity.getFlightTime() );

		return stats;
	}

}
