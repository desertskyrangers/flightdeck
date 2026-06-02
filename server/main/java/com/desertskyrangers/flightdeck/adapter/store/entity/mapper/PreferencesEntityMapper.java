package com.desertskyrangers.flightdeck.adapter.store.entity.mapper;

import com.desertskyrangers.flightdeck.adapter.store.entity.PreferencesEntity;
import com.desertskyrangers.flightdeck.adapter.store.entity.PreferencesProjection;
import com.desertskyrangers.flightdeck.core.model.User;
import com.desertskyrangers.flightdeck.util.Json;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PreferencesEntityMapper {

	public PreferencesProjection toProjection( User user, Map<String, Object> preferences ) {
		if( user == null || preferences == null ) return null;
		PreferencesEntity entity = new PreferencesEntity( preferences );
		return new PreferencesProjection().setId( user.id() ).setJson( Json.stringify( entity ) );
	}

	public Map<String, Object> toPreferences( PreferencesProjection projection ) {
		if( projection == null ) return null;
		return Json.objectify( projection.getJson(), PreferencesEntity.class );
	}

}
