package com.desertskyrangers.flightdeck.adapter.store.entity;

import com.desertskyrangers.flightdeck.util.AppColor;
import com.desertskyrangers.flightdeck.util.AppColorConverter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Entity
@Table( name = "aircraft" )
@Accessors( chain = true )
public class AircraftEntity {

	@Id
	private UUID id;

	@Column( nullable = false )
	private String name;

	private String type;

	private String make;

	private String model;

	private String status;

	private String connector;

	private Double wingspan;

	private Double length;

	private Double wingarea;

	private Double weight;

	@Column( name = "nightlights" )
	private Boolean nightLights;

	@Column( name = "flightcount" )
	private Integer flightCount;

	@Column( name = "flighttime" )
	private Long flightTime;

	@Column( nullable = false )
	private UUID owner;

	@Column( name = "ownertype", nullable = false )
	private String ownerType;

	@Column( name = "basecolor" )
	@Convert( converter = AppColorConverter.class )
	private AppColor baseColor;

	@Column( name = "trimcolor" )
	@Convert( converter = AppColorConverter.class )
	private AppColor trimColor;

}
