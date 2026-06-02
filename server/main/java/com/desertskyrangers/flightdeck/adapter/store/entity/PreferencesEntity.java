package com.desertskyrangers.flightdeck.adapter.store.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @deprecated Part of an old projection strategy see {@link ProjectionEntity}
 */
@Deprecated
public class PreferencesEntity extends HashMap<String, Object> {

	@SuppressWarnings( "unused" )
	public PreferencesEntity() {
		super();
	}

	public PreferencesEntity( Map<? extends String, ?> m ) {
		super( m );
	}

}
