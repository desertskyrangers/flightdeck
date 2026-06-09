package com.desertskyrangers.flightdeck.adapter.store.entity.mapper;

import com.desertskyrangers.flightdeck.adapter.store.entity.*;
import com.desertskyrangers.flightdeck.adapter.store.repo.GroupRepo;
import com.desertskyrangers.flightdeck.adapter.store.repo.MemberRepo;
import com.desertskyrangers.flightdeck.adapter.store.repo.UserRepo;
import com.desertskyrangers.flightdeck.core.model.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class MemberEntityMapper {

	private final MemberRepo memberRepo;

	private final UserRepo userRepo;

	private final GroupRepo groupRepo;

	public MemberEntityMapper( MemberRepo memberRepo, UserRepo userRepo, GroupRepo groupRepo ) {
		this.memberRepo = memberRepo;
		this.userRepo = userRepo;
		this.groupRepo = groupRepo;
	}

	public MemberEntity toEntity( Member member ) {
		if( member == null ) return null;

		MemberEntity entity = memberRepo.findById( member.id() ).orElse( new MemberEntity() );
		entity.setId( member.id() );
		if( member.user() != null ) {
			entity.setUser( userRepo.findById( member.user().id() ).orElse( null ) );
		}
		if( member.group() != null ) {
			entity.setGroup( groupRepo.findById( member.group().id() ).orElse( null ) );
		}
		entity.setStatus( member.status().title().toLowerCase() );
		return entity;
	}

	public Member toMember( MemberEntity entity ) {
		if( entity == null ) return null;

		Member member = toMemberShallow( entity );

		final Map<UUID, Group> groups = new HashMap<>();
		final Map<UUID, Member> members = new HashMap<>();
		final Map<UUID, Location> locations = new HashMap<>();
		final Map<UUID, User> users = new HashMap<>();
		members.put( entity.getId(), member );

		member.group( GroupEntityMapper.toGroupFromRelated( entity.getGroup(), groups, members, locations, users ) );
		member.user( UserEntity.toUserFromRelated( entity.getUser(), users, groups, locations, members ) );

		return member;
	}

	public static Member toMemberFromRelated( MemberEntity entity, Map<UUID, Member> members, Map<UUID, Group> groups, Map<UUID, Location> locations, Map<UUID, User> users ) {
		// If the member already exists, just return it
		Member member = members.get( entity.getId() );
		if( member != null ) return member;

		// Create the shallow version of the member and put it in the members map
		member = toMemberShallowInternal( entity );
		members.put( entity.getId(), member );

		// Link the member to related entities
		member.user( UserEntity.toUserFromRelated( entity.getUser(), users, groups, locations, members ) );
		member.group( GroupEntityMapper.toGroupFromRelated( entity.getGroup(), groups, members, locations, users ) );

		return member;
	}

	private Member toMemberShallow( MemberEntity entity ) {
		return toMemberShallowInternal( entity );
	}

	private static Member toMemberShallowInternal( MemberEntity entity ) {
		Member member = new Member();

		member.id( entity.getId() );
		member.status( Member.Status.valueOf( entity.getStatus().toUpperCase() ) );

		return member;
	}

}
