package com.desertskyrangers.flightdeck.core.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors( fluent = true )
public class Battery {

	public static final int MAX_CYCLES = 200;

	private UUID id = UUID.randomUUID();

	private String name;

	private String make;

	private String model;

	private Connector connector;

	private String unlistedConnector;

	private Status status;

	private Chemistry chemistry;

	private int cells;

	private int initialCycles;

	private int cycles;

	private int capacity;

	private int dischargeRating;

	private UUID owner;

	private OwnerType ownerType;

	private int flightCount;

	private long flightTime;

	public int life() {
		return (int)((100D * (Battery.MAX_CYCLES - cycles()) / Battery.MAX_CYCLES));
	}

	public enum Connector {

		XT30( "XT30" ),
		XT60( "XT60" ),
		XT90( "XT90" ),
		DEANS( "Deans T Plug" ),
		DEANS_MINI( "Deans Mini" ),
		EC2( "EC2" ),
		EC3( "EC3" ),
		EC5( "EC5" ),
		JST( "JST" ),
		MOLEX( "Molex" ),
		SERVO( "Servo" ),
		TRX( "TRX" ),
		UNLISTED("Unlisted");

		private final String name;

		Connector( String name ) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public static boolean isValid( String string ) {
			try {
				valueOf( string.toUpperCase() );
				return true;
			} catch( NullPointerException | IllegalArgumentException exception ) {
				return false;
			}
		}

		public static boolean isNotValid( String string ) {
			return !isValid( string );
		}

	}

	public enum Status {

		NEW( "New", true ),
		AVAILABLE( "Available", true ),
		DESTROYED( "Destroyed", false );

		private final String name;

		private final boolean airworthy;

		Status( String name, boolean airworthy ) {
			this.name = name;
			this.airworthy = airworthy;
		}

		public String getName() {
			return name;
		}

		public boolean isAirworthy() {
			return airworthy;
		}

		public static boolean isValid( String string ) {
			try {
				valueOf( string.toUpperCase() );
				return true;
			} catch( NullPointerException | IllegalArgumentException exception ) {
				return false;
			}
		}

		public static boolean isNotValid( String string ) {
			return !isValid( string );
		}

	}

	public enum Chemistry {

		NICD( "NiCd", 1.4, 1.2, 1.0 ),
		LIPO( "LiPo", 4.2, 3.7, 3.0 ),
		LIFE( "LiFe", 3.65, 3.2, 2.5 ),
		LIHV( "LiHV", 4.4, 3.7, 3.0 ),
		LIION( "LiIon", 4.2, 3.6, 3.0 ),
		NIMH( "NiMH", 1.5, 1.2, 1.0 ),
		NIZN( "NiZn", 1.85, 1.65, 1.2 ),
		LEAD( "Lead", 2.1, 2.1, 1.83 );

		private final String name;

		private final double maxVoltsPerCell;
		private final double voltsPerCell;

		private final double minVoltsPerCell;

		Chemistry( String name, double maxVoltsPerCell, double voltsPerCell, double minVoltsPerCell ) {
			this.name = name;
			this.maxVoltsPerCell = maxVoltsPerCell;
			this.voltsPerCell = voltsPerCell;
			this.minVoltsPerCell = minVoltsPerCell;
		}

		public String getName() {
			return name;
		}

		public double getMaxVoltsPerCell() {
			return maxVoltsPerCell;
		}

		public double getVoltsPerCell() {
			return voltsPerCell;
		}

		public double getMinVoltsPerCell() {
			return minVoltsPerCell;
		}

		public static boolean isValid( String string ) {
			try {
				valueOf( string.toUpperCase() );
				return true;
			} catch( NullPointerException | IllegalArgumentException exception ) {
				return false;
			}
		}

		public static boolean isNotValid( String string ) {
			return !isValid( string );
		}

	}

}
