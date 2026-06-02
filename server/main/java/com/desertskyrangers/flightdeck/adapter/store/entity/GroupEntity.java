package com.desertskyrangers.flightdeck.adapter.store.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table( name = "org" )
@Accessors( chain = true )
public class GroupEntity {

	@Id
	private UUID id;

	@Column( nullable = false )
	private String type;

	@Column( nullable = false )
	private String name;

	@Column( name = "dashboardid" )
	private UUID dashboardId;

	@ManyToMany( fetch = FetchType.EAGER )
	@JoinTable( name = "member", joinColumns = @JoinColumn( name = "orgid" ), inverseJoinColumns = @JoinColumn( name = "userid" ) )
	@EqualsAndHashCode.Exclude
	@ToString.Exclude()
	private Set<UserEntity> users = new HashSet<>();

	@OneToMany( mappedBy = "group", fetch = FetchType.EAGER )
	@EqualsAndHashCode.Exclude
	@ToString.Exclude()
	private Set<MemberEntity> memberships = new HashSet<>();

}
