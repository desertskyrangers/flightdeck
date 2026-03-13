import Config from "../AppConfig";
import ApiPath from "./ApiPath";
import ApiService from "./ApiService"

class LocationService extends ApiService {

	getLocation(id, successCallback, failureCallback) {
		this.fetch(Config.API_URL + ApiPath.LOCATION + "/" + id, {
			method: 'GET'
		}).then((response) => {
			successCallback(response.data)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	updateLocation(location, successCallback, failureCallback) {
		const method = location.id === 'new' ? 'POST' : 'PUT'
		this.fetch(Config.API_URL + ApiPath.LOCATION, {
			method: method,
			body: JSON.stringify(location)
		}).then((response) => {
			successCallback(response.data)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	deleteLocation(id, successCallback, failureCallback) {
		this.fetch(Config.API_URL + ApiPath.LOCATION, {
			method: 'DELETE',
			body: JSON.stringify({id: id})
		}).then((response) => {
			successCallback(response.data)
		}).catch((error) => {
			failureCallback(error)
		})
	}

}

const instance = new LocationService()
Object.freeze(instance)
export default instance
