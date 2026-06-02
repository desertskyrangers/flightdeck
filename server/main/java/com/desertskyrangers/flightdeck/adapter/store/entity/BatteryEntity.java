package com.desertskyrangers.flightdeck.adapter.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Entity
@Table( name = "battery" )
@Accessors( chain = true )
public class BatteryEntity {

	@Id
	private UUID id;

	private String name;

	private String make;

	private String model;

	private String connector;

	@Column( name = "unlistedconnector" )
	private String unlistedConnector;

	private String status;

	@Column( name = "type" )
	private String chemistry;

	private int cells;

	@Column( name = "initialcycles" )
	private int initialCycles;

	private int cycles;

	private int capacity;

	@Column( name = "dischargerating" )
	private int dischargeRating;

	@Column( name = "flightcount" )
	private Integer flightCount;

	@Column( name = "flighttime" )
	private Long flightTime;

	@Column( nullable = false )
	private UUID owner;

	@Column( name = "ownertype", nullable = false )
	private String ownerType;

}
