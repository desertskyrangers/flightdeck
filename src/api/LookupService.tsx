import ApiService from "./ApiService";
import Config from "../AppConfig";

class LookupService extends ApiService {

	getAircraftStatuses(successCallback, failureCallback) {
		this.fetch(Config.API_URL + `/api/lookup/aircraft/status`, {
			method: 'GET'
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	getAircraftTypes(successCallback, failureCallback) {
		this.fetch(Config.API_URL + `/api/lookup/aircraft/type`, {
			method: 'GET'
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	getBatteryConnectors(successCallback, failureCallback) {
		this.fetch(Config.API_URL + `/api/lookup/battery/connector`, {
			method: 'GET'
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	getBatteryStatuses(successCallback, failureCallback) {
		this.fetch(Config.API_URL + `/api/lookup/battery/status`, {
			method: 'GET'
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	getBatteryTypes(successCallback, failureCallback) {
		this.fetch(Config.API_URL + `/api/lookup/battery/type`, {
			method: 'GET'
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	getGroupTypes(successCallback, failureCallback) {
		this.fetch(Config.API_URL + `/api/lookup/group/type`, {
			method: 'GET'
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	getSmsCarriers(successCallback, failureCallback) {
		this.fetch(Config.API_URL + `/api/lookup/sms/carriers`, {
			method: 'GET'
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

}

const instance = new LookupService()
Object.freeze(instance)
export default instance
