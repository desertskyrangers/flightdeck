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
@Table( name = "verification" )
@Accessors( chain = true )
public class VerificationEntity {

	@Id
	private UUID id;

	@Column( name = "userid" )
	private UUID userId;

	private Long timestamp;

	private String code;

	private String type;

}
