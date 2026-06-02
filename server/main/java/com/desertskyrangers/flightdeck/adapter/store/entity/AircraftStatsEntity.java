package com.desertskyrangers.flightdeck.adapter.store.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors( chain = true )
public class AircraftStatsEntity {

	private UUID id;

	private String name;

	private String type;

	private long lastFlightTimestamp;

	private int flightCount;

	private long flightTime;

}
