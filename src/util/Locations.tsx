// The average radius of the Earth in meters
// https://en.wikipedia.org/wiki/Earth_radius
const rE = 6.3781e6;

//const DEGREES_PER_RADIAN = 180.0 / Math.PI;

const RADIANS_PER_DEGREE = Math.PI / 180.0;


class Locations {

	CUSTOM_LOCATION_ID = 'b9902649-9a9f-4d96-a219-3f2a52c1357e'
	DEVICE_LOCATION_ID = '313d9657-abbd-4f5c-a9c3-9618a2574e18'
	NO_LOCATION_ID = 'a65a59cb-45f5-43c0-94c8-d6489d1ca19f'

	contains(latitude, longitude, location) {
		return this.containsByDiameter(latitude, longitude, location)
	}

	containsByDiameter(latitude, longitude, location) {
		return this.isContainedByRadius(latitude, longitude, location, 0.5 * location.size)
	}

	containsByRadius(latitude, longitude, location) {
		return this.isContainedByRadius(latitude, longitude, location, location.size)
	}

	isContainedByRadius(latitude, longitude, location, size) {
		const dx = latitude - location.latitude;
		const dy = longitude - location.longitude;
		const offset =this.degreesToMetersOnEarth( Math.sqrt(dx * dx + dy * dy) );
		return offset <= size;
	}

	/**
	 * Convert a distance in decimal degrees to meters on the surface of the
	 * Earth. This method reasonably accurate up to distances of a few degrees.
	 *
	 * @param distance A distance in decimal degrees
	 * @return The distance in meters
	 */
	degreesToMetersOnEarth(distance) {
		return Math.sin(distance * RADIANS_PER_DEGREE) * rE;
	}

}

const instance = new Locations()
Object.freeze(instance)
export default instance
