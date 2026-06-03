package com.desertskyrangers.flightdeck.adapter.store.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table( name = "flight" )
@Accessors( chain = true )
public class FlightEntity {

	@Id
	private UUID id;

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
	@JoinColumn( name = "pilotid", nullable = false )
	private UserEntity pilot;

	@Column( name = "unlistedpilot" )
	private String unlistedPilot;

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
	@JoinColumn( name = "observerid" )
	private UserEntity observer;

	@Column( name = "unlistedobserver" )
	private String unlistedObserver;

	@ManyToOne( fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
	@JoinColumn( name = "aircraftid", nullable = false )
	private AircraftEntity aircraft;

	@ManyToMany( fetch = FetchType.EAGER )
	@JoinTable( name = "flightbattery", joinColumns = @JoinColumn( name = "flightid" ), inverseJoinColumns = @JoinColumn( name = "batteryid" ) )
	@EqualsAndHashCode.Exclude
	private Set<BatteryEntity> batteries;

	private long timestamp;

	private int duration;

	@Column( name = "locationid" )
	private UUID locationId;

	private double latitude;

	private double longitude;

	private double altitude;

	@Column( length = 1000 )
	private String notes;

	// departure location
	// arrival location

	// weather ?
	// airspace ?

}
