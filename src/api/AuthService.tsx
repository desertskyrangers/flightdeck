import ApiService from "./ApiService"
import TokenService from "./TokenService"
import Config from "../AppConfig";
import ApiPath from "./ApiPath";

class AuthService extends ApiService {

	register(username, password, email, successCallback, failureCallback) {
		this.fetchNoAuth(Config.API_URL + ApiPath.AUTH_REGISTER, {
			method: 'POST',
			body: JSON.stringify({
				username,
				password,
				email
			})
		}).then((response) => {
			TokenService.setToken(response.jwt.token)
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	resend(id, successCallback, failureCallback) {
		this.fetchNoAuth(Config.API_URL + ApiPath.AUTH_RESEND, {
			method: 'POST',
			body: JSON.stringify({
				"id": id
			})
		}).then(response => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	verify(id, code, successCallback, failureCallback) {
		this.fetchNoAuth(Config.API_URL + ApiPath.AUTH_VERIFY, {
			method: 'POST',
			body: JSON.stringify({
				"id": id,
				"code": code
			})
		}).then(response => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	login(username, password, successCallback, failureCallback) {
		this.fetchNoAuth(Config.API_URL + ApiPath.AUTH_LOGIN, {
			method: 'POST',
			body: JSON.stringify({
				username,
				password
			})
		}).then(response => {
			TokenService.setToken(response.jwt.token)
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	recover(username, successCallback, failureCallback) {
		this.fetchNoAuth(Config.API_URL + ApiPath.AUTH_RECOVER, {
			method: 'POST',
			body: JSON.stringify({
				"username": username
			})
		}).then(response => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	reset(id, password, successCallback, failureCallback) {
		this.fetchNoAuth(Config.API_URL + ApiPath.AUTH_RESET, {
			method: 'POST',
			body: JSON.stringify({
				"id": id,
				"password": password
			})
		}).then(response => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	loggedIn() {
		return TokenService.isAuthenticated()
	}

	logout(callback, failureCallback) {
		// Call api logout before expiring the token
		this.fetch(Config.API_URL + ApiPath.AUTH_LOGOUT, {
			method: 'GET'
		}).then(response => {
			callback()
		}).catch(failure => {
			failureCallback(failure)
		})

		// Expire the token after calling api logout
		TokenService.expire()
		window.location.assign('/')
	}

}

const instance = new AuthService()
Object.freeze(instance)
export default instance
