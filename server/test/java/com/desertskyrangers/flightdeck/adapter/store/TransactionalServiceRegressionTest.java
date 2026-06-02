package com.desertskyrangers.flightdeck.adapter.store;

import com.desertskyrangers.flightdeck.BaseTest;
import com.desertskyrangers.flightdeck.adapter.store.entity.GroupEntity;
import com.desertskyrangers.flightdeck.adapter.store.repo.GroupRepo;
import com.desertskyrangers.flightdeck.adapter.store.repo.MemberRepo;
import com.desertskyrangers.flightdeck.adapter.store.repo.UserRepo;
import com.desertskyrangers.flightdeck.core.model.Group;
import com.desertskyrangers.flightdeck.core.model.Member;
import com.desertskyrangers.flightdeck.core.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionalServiceRegressionTest extends BaseTest {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private GroupRepo groupRepo;

	@Autowired
	private MemberRepo memberRepo;

	@Autowired
	private StateRetrievingService stateRetrievingService;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Test
	void testFindGroupHandlesLazyRelationships() {
		// given
		TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
		UUID groupId = transactionTemplate.execute(status -> {
			User user = statePersisting.upsert(createTestUser("Test User", "testuser@example.com"));
			Group group = statePersisting.upsert(createTestGroup("Test Group", Group.Type.CLUB));
			
			// Use StatePersistingService to create the membership which should correctly link things
			Member member = new Member().user(user).group(group).status(Member.Status.ACCEPTED);
			statePersisting.upsert(member);

			return group.id();
		});

		// when
		Group group = stateRetrievingService.findGroup(groupId).orElse(null);

		// then
		assertThat(group).isNotNull();
		assertThat(group.name()).isEqualTo("Test Group");

		// Check memberships
		// This used to fail with LazyInitializationException when StateRetrievingService was not @Transactional
		// because findMemberships(group) triggers mapping which accesses the proxy for the group.
		Set<Member> members = stateRetrievingService.findMemberships(group);
		assertThat(members).hasSize(1);
		assertThat(members.iterator().next().user().username()).isEqualTo("Test User");
	}
}
