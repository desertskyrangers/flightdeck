package com.desertskyrangers.flightdeck.adapter.store.entity.mapper;

import com.desertskyrangers.flightdeck.adapter.store.entity.*;
import com.desertskyrangers.flightdeck.adapter.store.repo.GroupRepo;
import com.desertskyrangers.flightdeck.adapter.store.repo.TokenRepo;
import com.desertskyrangers.flightdeck.core.model.*;
import com.desertskyrangers.flightdeck.util.SmsCarrier;
import com.desertskyrangers.flightdeck.util.Text;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserEntityMapper {

	private final GroupRepo groupRepo;

	private final TokenRepo tokenRepo;

	private final TokenEntityMapper tokenMapper;

	public UserEntityMapper( GroupRepo groupRepo, TokenRepo tokenRepo, TokenEntityMapper tokenMapper ) {
		this.groupRepo = groupRepo;
		this.tokenRepo = tokenRepo;
		this.tokenMapper = tokenMapper;
	}

	public UserEntity toEntity( User user ) {
		if( user == null ) return null;

		UserEntity entity = new UserEntity();
		entity.setId( user.id() );
		entity.setUsername( user.username() );
		entity.setFirstName( user.firstName() );
		entity.setLastName( user.lastName() );
		entity.setPreferredName( user.preferredName() );
		entity.setCallSign( user.callSign() );
		entity.setEmail( user.email() );
		entity.setEmailVerified( user.emailVerified() );
		entity.setSmsNumber( user.smsNumber() );
		entity.setDashboardId( user.dashboardId() );
		entity.setPublicDashboardId( user.publicDashboardId() );
		if( user.smsCarrier() != null ) entity.setSmsCarrier( user.smsCarrier().name().toLowerCase() );
		entity.setSmsVerified( user.smsVerified() );
		entity.setRoles( user.roles() );

		if( user.groups() != null ) {
			entity.setGroups( user.groups().stream()
				.map( g -> groupRepo.getReferenceById( g.id() ) )
				.collect( Collectors.toSet() ) );
		}

		if( user.tokens() != null ) {
			entity.setTokens( user.tokens().stream()
				.map( t -> tokenRepo.getReferenceById( t.id() ) )
				.collect( Collectors.toSet() ) );
		}

		return entity;
	}

	public User toUser( UserEntity entity ) {
		if( entity == null ) return null;

		User user = toUserShallow( entity );

		final Map<UUID, User> users = new HashMap<>();
		final Map<UUID, Group> groups = new HashMap<>();
		final Map<UUID, Location> locations = new HashMap<>();
		final Map<UUID, Member> members = new HashMap<>();
		users.put( entity.getId(), user );

		user.groups( entity.getGroups().stream().map( e -> GroupEntityMapper.toGroupFromRelated( e, groups, members, locations, users ) ).collect( Collectors.toSet() ) );

		return user;
	}

	private User toUserShallow( UserEntity entity ) {
		User user = new User();

		user.id( entity.getId() );
		user.username( entity.getUsername() );
		user.firstName( entity.getFirstName() );
		user.lastName( entity.getLastName() );
		user.preferredName( entity.getPreferredName() );
		user.callSign( entity.getCallSign() );
		user.email( entity.getEmail() );
		user.emailVerified( entity.getEmailVerified() != null && entity.getEmailVerified() );
		user.smsNumber( entity.getSmsNumber() );
		user.dashboardId( entity.getDashboardId() );
		user.publicDashboardId( entity.getPublicDashboardId() );
		if( Text.isNotBlank( entity.getSmsCarrier() ) ) user.smsCarrier( SmsCarrier.valueOf( entity.getSmsCarrier().toUpperCase() ) );
		user.smsVerified( entity.getSmsVerified() != null && entity.getSmsVerified() );
		user.roles( entity.getRoles() );
		user.tokens( entity.getTokens().stream().map( c -> tokenMapper.toUserToken( c ).user( user ) ).collect( Collectors.toSet() ) );

		return user;
	}

}
