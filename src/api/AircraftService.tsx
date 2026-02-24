import Config from "../AppConfig";
import ApiPath from "./ApiPath";
import ApiService from "./ApiService"

class AircraftService extends ApiService {

	getAircraft(id, successCallback, failureCallback) {
		this.fetch(Config.API_URL + ApiPath.AIRCRAFT + "/" + id, {
			method: 'GET'
		}).then((response) => {
			successCallback(response.data)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	updateAircraft(aircraft, successCallback, failureCallback) {
		const method = aircraft.id === 'new' ? 'POST' : 'PUT'
		this.fetch(Config.API_URL + ApiPath.AIRCRAFT, {
			method: method,
			body: JSON.stringify(aircraft)
		}).then((response) => {
			successCallback(response.data)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	deleteAircraft(id, successCallback, failureCallback) {
		this.fetch(Config.API_URL + ApiPath.AIRCRAFT, {
			method: 'DELETE',
			body: JSON.stringify({id: id})
		}).then((response) => {
			successCallback(response.data)
		}).catch((error) => {
			failureCallback(error)
		})
	}

}

const instance = new AircraftService()
Object.freeze(instance)
export default instance
