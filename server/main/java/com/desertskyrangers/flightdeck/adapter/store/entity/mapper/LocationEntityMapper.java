package com.desertskyrangers.flightdeck.adapter.store.entity.mapper;

import com.desertskyrangers.flightdeck.adapter.store.entity.*;
import com.desertskyrangers.flightdeck.adapter.store.repo.UserRepo;
import com.desertskyrangers.flightdeck.core.model.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class LocationEntityMapper {

	private final UserRepo userRepo;

	public LocationEntityMapper( UserRepo userRepo ) {
		this.userRepo = userRepo;
	}

	public LocationEntity toEntity( Location location ) {
		if( location == null ) return null;

		LocationEntity entity = new LocationEntity();
		entity.setId( location.id() );
		entity.setLatitude( location.latitude() );
		entity.setLongitude( location.longitude() );
		entity.setAltitude( location.altitude() );
		if( location.user() != null ) entity.setUser( userRepo.getReferenceById( location.user().id() ) );
		entity.setName( location.name() );
		entity.setSize( location.size() );
		entity.setStatus( location.status().name().toLowerCase() );

		return entity;
	}

	public Location toLocation( LocationEntity entity ) {
		if( entity == null ) return null;

		Location location = toLocationShallow( entity );

		final Map<UUID, Group> groups = new HashMap<>();
		final Map<UUID, Location> locations = new HashMap<>();
		final Map<UUID, Member> members = new HashMap<>();
		final Map<UUID, User> users = new HashMap<>();
		locations.put( entity.getId(), location );

		location.user( UserEntity.toUserFromRelated( entity.getUser(), users, groups, locations, members ) );

		return location;
	}

	private Location toLocationShallow( LocationEntity entity ) {
		Location location = new Location();

		location.id( entity.getId() );
		location.latitude( entity.getLatitude() );
		location.longitude( entity.getLongitude() );
		location.altitude( entity.getAltitude() );
		location.name( entity.getName() );
		location.size( entity.getSize() );
		location.status( Location.Status.valueOf( entity.getStatus().toUpperCase() ) );

		return location;
	}

}
