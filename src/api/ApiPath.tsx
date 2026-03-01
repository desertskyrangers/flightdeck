class ApiPath {

	ROOT = "/api"

	AIRCRAFT = this.ROOT + "/aircraft"

	AUTH = this.ROOT + "/auth"

	AUTH_LOGIN = this.AUTH + "/login"

	AUTH_LOGOUT = this.AUTH + "/logout"

	AUTH_RECOVER = this.AUTH + "/recover"

	AUTH_REGISTER = this.AUTH + "/register"

	AUTH_RESEND = this.AUTH + "/resend"

	AUTH_RESET = this.AUTH + "/reset"

	AUTH_VERIFY = this.AUTH + "/verify"

	BATTERY = this.ROOT + "/battery"

	FLIGHT = this.ROOT + "/flight"

	GROUP = this.ROOT + "/group"

	GROUP_AVAILABLE = this.GROUP + "/available"

	GROUP_INVITE = this.GROUP + "/invite"

	GROUP_MEMBERSHIP = this.GROUP + "/membership"

	LOCATION = this.ROOT + "/location"

	MEMBERSHIP = this.ROOT + "/membership"

	USER = this.ROOT + "/user"

	USER_AIRCRAFT = this.USER + "/aircraft"

	USER_BATTERY = this.USER + "/battery"

	USER_FLIGHT = this.USER + "/flight"

	USER_GROUP = this.USER + "/group"

	USER_LOCATION = this.USER + "/location"

	USER_MEMBERSHIP = this.USER + "/membership"

	USER_PREFERENCES = this.USER + "/preferences"

	USER_LOOKUP = this.USER + "/lookup"

	USER_AIRCRAFT_LOOKUP = this.USER_LOOKUP + "/aircraft"

	USER_BATTERY_LOOKUP = this.USER_LOOKUP + "/battery"

	USER_LOCATION_LOOKUP = this.USER_LOOKUP + "/location"

	USER_OBSERVER_LOOKUP = this.USER_LOOKUP + "/observer"

	USER_PILOT_LOOKUP = this.USER_LOOKUP + "/pilot"
}

const instance = new ApiPath()
Object.freeze(instance)
export default instance
