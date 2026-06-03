package com.desertskyrangers.flightdeck.adapter.store.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Entity
@Table( name = "location" )
@Accessors( chain = true )
public class LocationEntity {

	@Id
	private UUID id;

	private double latitude;

	private double longitude;

	private double altitude;

	@ManyToOne( optional = false, fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE } )
	@JoinColumn( name = "userid", nullable = false, updatable = false )
	private UserEntity user;

	@Column( length = 160 )
	private String name;

	private double size;

	private String status;

}
