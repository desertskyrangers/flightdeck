package com.desertskyrangers.flightdeck.adapter.store.entity.mapper;

import com.desertskyrangers.flightdeck.adapter.store.entity.VerificationEntity;
import com.desertskyrangers.flightdeck.core.model.Verification;
import org.springframework.stereotype.Component;

@Component
public class VerificationEntityMapper {

	public VerificationEntity toEntity( Verification verification ) {
		if( verification == null ) return null;

		VerificationEntity entity = new VerificationEntity();
		entity.setId( verification.id() );
		entity.setUserId( verification.userId() );
		entity.setTimestamp( verification.timestamp() );
		entity.setCode( verification.code() );
		entity.setType( verification.type() );

		return entity;
	}

	public Verification toVerification( VerificationEntity entity ) {
		if( entity == null ) return null;

		Verification verification = new Verification();
		verification.id( entity.getId() );
		verification.userId( entity.getUserId() );
		verification.timestamp( entity.getTimestamp() );
		verification.code( entity.getCode() );
		verification.type( entity.getType() );

		return verification;
	}

}
