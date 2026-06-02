package com.desertskyrangers.flightdeck.adapter.store.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Entity
@Table( name = "member" )
@Accessors( chain = true )
public class MemberEntity {

	@Id
	private UUID id;

	@ManyToOne( optional = false, fetch = FetchType.EAGER )
	@JoinColumn( name = "userid", nullable = false, updatable = false )
	private UserEntity user;

	@ManyToOne( optional = false, fetch = FetchType.EAGER )
	@JoinColumn( name = "orgid", nullable = false, updatable = false )
	private GroupEntity group;

	private String status;

}
