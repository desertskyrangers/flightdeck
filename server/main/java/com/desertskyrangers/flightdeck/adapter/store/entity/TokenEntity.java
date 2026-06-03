package com.desertskyrangers.flightdeck.adapter.store.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Entity
@Table( name = "token" )
@Accessors( chain = true )
public class TokenEntity {

	@Id
	private UUID id;

	@ManyToOne( optional = false, fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE } )
	@JoinColumn( name = "userid", nullable = false, updatable = false )
	private UserEntity user;

	@Column( unique = true )
	private String principal;

	private String credential;

}
