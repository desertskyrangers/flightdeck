class AppPath {

	ROOT = ""

	// Not protected (does not require authentication)
	ABOUT = this.ROOT + "/about"
	LEGAL = this.ROOT + "/legal"
	LOGIN = this.ROOT + "/login"
	PRIVACY = this.ROOT + "/privacy"
	RECOVER = this.ROOT + "/recover"
	REGISTER = this.ROOT + "/register"
	RESET = this.ROOT + "/reset"
	VERIFY = this.ROOT + "/verify"

	// Protected (requires authentication)
	HOME = this.ROOT + "/"
	DASHBOARD = this.ROOT + "/dashboard"

	AIRCRAFT = this.ROOT + "/aircraft"
	GROUP_AIRCRAFT = this.AIRCRAFT + "/group"
	USER_AIRCRAFT = this.AIRCRAFT + "/user"

	BATTERY = this.ROOT + "/battery"
	USER_BATTERIES = this.BATTERY + "/user"

	LOCATION = this.ROOT + "/location"
	USER_LOCATIONS = this.LOCATION + "/user"

	EXPORT = this.ROOT + "/export"

	FLIGHT = this.ROOT + "/flight"
	FLIGHT_TIMER = this.FLIGHT + "/timer"

	FLIGHTS = this.ROOT + "/flights"
	GROUP_FLIGHTS = this.FLIGHTS + "/group"
	USER_FLIGHTS = this.FLIGHTS + "/user"

	GROUP = this.ROOT + "/group"
	USER_GROUPS = this.GROUP + "/user"

	SETUP = this.ROOT + "/settings"

	USER = this.ROOT + "/user"
	PASSWORD = this.USER + "/password"
	PREFERENCES = this.USER + "/preferences"
	PROFILE = this.USER + "/profile"
	SECURITY = this.USER + "/security"
}

const instance = new AppPath()
Object.freeze(instance)
export default instance
