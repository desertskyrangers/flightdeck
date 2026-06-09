package com.desertskyrangers.flightdeck.adapter.store.entity.mapper;

import com.desertskyrangers.flightdeck.adapter.store.entity.TokenEntity;
import com.desertskyrangers.flightdeck.adapter.store.entity.UserEntity;
import com.desertskyrangers.flightdeck.adapter.store.repo.UserRepo;
import com.desertskyrangers.flightdeck.core.model.User;
import com.desertskyrangers.flightdeck.core.model.UserToken;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class TokenEntityMapper {

	private final UserRepo userRepo;

	public TokenEntityMapper( UserRepo userRepo ) {
		this.userRepo = userRepo;
	}

	public TokenEntity toEntity( UserToken token ) {
		if( token == null ) return null;

		TokenEntity entity = new TokenEntity();
		entity.setId( token.id() );
		if( token.user() != null ) entity.setUser( userRepo.getReferenceById( token.user().id() ) );
		entity.setPrincipal( token.principal() );
		entity.setCredential( token.credential() );
		return entity;
	}

	public UserToken toUserToken( TokenEntity entity ) {
		return toUserToken( entity, false );
	}

	public UserToken toUserTokenDeep( TokenEntity entity ) {
		return toUserToken( entity, true );
	}

	private UserToken toUserToken( TokenEntity entity, boolean includeUser ) {
		if( entity == null ) return null;

		UserToken token = new UserToken();
		token.id( entity.getId() );
		token.principal( entity.getPrincipal() );
		token.credential( entity.getCredential() );

		if( includeUser && entity.getUser() != null ) {
			// Using UserEntity.toUser(entity.getUser()) for now to match original behavior
			// but in a more consistent way, maybe we should use UserEntityMapper if we had it easily available.
			// However, original code used UserEntity.toUser.
			token.user( UserEntity.toUser( entity.getUser() ) );
		}

		return token;
	}

}
