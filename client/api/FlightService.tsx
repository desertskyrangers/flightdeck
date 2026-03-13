import Config from "../AppConfig";
import ApiPath from "./ApiPath";
import ApiService from "./ApiService"

class FlightService extends ApiService {

	getFlight(id, successCallback, failureCallback) {
		this.fetch(Config.API_URL + ApiPath.FLIGHT + "/" + id, {
			method: 'GET'
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	updateFlight(flight, successCallback, failureCallback) {
		const method = flight.id === 'new' ? 'POST' : 'PUT'
		this.fetch(Config.API_URL + ApiPath.FLIGHT, {
			method: method,
			body: JSON.stringify(flight)
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	deleteFlight(id, successCallback, failureCallback) {
		this.fetch(Config.API_URL + ApiPath.FLIGHT, {
			method: 'DELETE',
			body: JSON.stringify({id: id})
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

}

const instance = new FlightService()
Object.freeze(instance)
export default instance
