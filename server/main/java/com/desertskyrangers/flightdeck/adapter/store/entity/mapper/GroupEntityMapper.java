package com.desertskyrangers.flightdeck.adapter.store.entity.mapper;

import com.desertskyrangers.flightdeck.adapter.store.entity.*;
import com.desertskyrangers.flightdeck.adapter.store.repo.MemberRepo;
import com.desertskyrangers.flightdeck.adapter.store.repo.UserRepo;
import com.desertskyrangers.flightdeck.core.model.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class GroupEntityMapper {

	private final UserRepo userRepo;

	private final MemberRepo memberRepo;

	public GroupEntityMapper( UserRepo userRepo, MemberRepo memberRepo ) {
		this.userRepo = userRepo;
		this.memberRepo = memberRepo;
	}

	public GroupEntity toEntity( Group group ) {
		if( group == null ) return null;

		GroupEntity entity = new GroupEntity();
		entity.setId( group.id() );
		entity.setType( group.type().name().toLowerCase() );
		entity.setName( group.name() );
		entity.setDashboardId( group.dashboardId() );

		if( group.users() != null ) {
			entity.setUsers( group.users().stream()
				.map( u -> userRepo.getReferenceById( u.id() ) )
				.collect( Collectors.toSet() ) );
		}

		if( group.members() != null ) {
			entity.setMemberships( group.members().stream()
				.map( m -> memberRepo.getReferenceById( m.id() ) )
				.collect( Collectors.toSet() ) );
		}

		return entity;
	}

	public Group toGroup( GroupEntity entity ) {
		if( entity == null ) return null;

		Group group = toGroupShallow( entity );

		final Map<UUID, Group> groups = new HashMap<>();
		final Map<UUID, Member> members = new HashMap<>();
		final Map<UUID, Location> locations = new HashMap<>();
		final Map<UUID, User> users = new HashMap<>();

		groups.put( entity.getId(), group );

		group.members( entity.getMemberships().stream().map( e -> MemberEntityMapper.toMemberFromRelated( e, members, groups, locations, users ) ).collect( Collectors.toSet() ) );
		group.users( entity.getUsers().stream().map( e -> UserEntity.toUserFromRelated( e, users, groups, locations, members ) ).collect( Collectors.toSet() ) );

		return group;
	}

	public static Group toGroupFromRelated( GroupEntity entity, Map<UUID, Group> groups, Map<UUID, Member> members, Map<UUID, Location> locations, Map<UUID, User> users ) {
		// If the group already exists, just return it
		Group group = groups.get( entity.getId() );
		if( group != null ) return group;

		// Create the shallow version of the group and put it in the groups map
		group = toGroupShallowInternal( entity );
		groups.put( entity.getId(), group );

		// Link the group to related entities
		group.members( entity.getMemberships().stream().map( e -> MemberEntityMapper.toMemberFromRelated( e, members, groups, locations, users ) ).collect( Collectors.toSet() ) );
		group.users( entity.getUsers().stream().map( e -> UserEntity.toUserFromRelated( e, users, groups, locations, members ) ).collect( Collectors.toSet() ) );
		return group;
	}

	private Group toGroupShallow( GroupEntity entity ) {
		return toGroupShallowInternal( entity );
	}

	private static Group toGroupShallowInternal( GroupEntity entity ) {
		Group group = new Group();

		group.id( entity.getId() );
		group.type( Group.Type.valueOf( entity.getType().toUpperCase() ) );
		group.name( entity.getName() );
		group.dashboardId( entity.getDashboardId() );

		return group;
	}

}
