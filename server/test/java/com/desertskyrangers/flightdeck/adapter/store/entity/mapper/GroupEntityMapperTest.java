package com.desertskyrangers.flightdeck.adapter.store.entity.mapper;

import com.desertskyrangers.flightdeck.adapter.store.entity.GroupEntity;
import com.desertskyrangers.flightdeck.adapter.store.entity.MemberEntity;
import com.desertskyrangers.flightdeck.adapter.store.repo.GroupRepo;
import com.desertskyrangers.flightdeck.adapter.store.repo.MemberRepo;
import com.desertskyrangers.flightdeck.adapter.store.repo.UserRepo;
import com.desertskyrangers.flightdeck.core.model.Group;
import com.desertskyrangers.flightdeck.core.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class GroupEntityMapperTest {

	private GroupRepo groupRepo;

	private UserRepo userRepo;

	private MemberRepo memberRepo;

	private GroupEntityMapper groupEntityMapper;

	@BeforeEach
	void setUp() {
		groupRepo = Mockito.mock( GroupRepo.class );
		userRepo = Mockito.mock( UserRepo.class );
		memberRepo = Mockito.mock( MemberRepo.class );
		groupEntityMapper = new GroupEntityMapper( groupRepo, userRepo, memberRepo );
	}

	@Test
	void testToEntityWithMissingMemberDoesNotThrowException() {
		// given
		UUID activeMemberId = UUID.randomUUID();
		UUID missingMemberId = UUID.randomUUID();

		Member activeMember = new Member().id( activeMemberId );
		Member missingMember = new Member().id( missingMemberId );

		Group group = new Group()
			.id( UUID.randomUUID() )
			.type( Group.Type.GROUP )
			.name( "Test Group" )
			.members( Set.of( activeMember, missingMember ) );

		MemberEntity activeMemberEntity = new MemberEntity().setId( activeMemberId );

		// Mock memberRepo to return the active member but return empty for the missing member
		when( groupRepo.findById( Mockito.any() ) ).thenReturn( Optional.empty() );
		when( memberRepo.findById( activeMemberId ) ).thenReturn( Optional.of( activeMemberEntity ) );
		when( memberRepo.findById( missingMemberId ) ).thenReturn( Optional.empty() );

		// when
		GroupEntity entity = groupEntityMapper.toEntity( group );

		// then
		assertThat( entity ).isNotNull();
		assertThat( entity.getMemberships() ).hasSize( 1 );
		assertThat( entity.getMemberships().iterator().next().getId() ).isEqualTo( activeMemberId );
	}
}
