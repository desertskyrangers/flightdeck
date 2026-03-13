import Config from "../AppConfig";
import ApiPath from "./ApiPath";
import ApiService from "./ApiService"

class BatteryService extends ApiService {

	getBattery(id, successCallback, failureCallback) {
		this.fetch(Config.API_URL + ApiPath.BATTERY + "/" + id, {
			method: 'GET'
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	updateBattery(battery, successCallback, failureCallback) {
		const method = battery.id === 'new' ? 'POST' : 'PUT'
		this.fetch(Config.API_URL + ApiPath.BATTERY, {
			method: method,
			body: JSON.stringify(battery)
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	deleteBattery(id, successCallback, failureCallback) {
		this.fetch(Config.API_URL + ApiPath.BATTERY, {
			method: 'DELETE',
			body: JSON.stringify({id: id})
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

}

const instance = new BatteryService()
Object.freeze(instance)
export default instance
