import Config from "../AppConfig";
import ApiPath from "./ApiPath";
import ApiService from "./ApiService"

class GroupService extends ApiService {

	callout(id,successCallback, failureCallback) {
		this.fetch(Config.API_URL + ApiPath.GROUP + `/${id}/callout`, {
			method: 'GET'
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	dashboard(id,successCallback, failureCallback) {
		this.fetch(Config.API_URL + ApiPath.GROUP + `/${id}/dashboard`, {
			method: 'GET'
		}).then((response) => {
			successCallback(response.data)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	getAvailableGroups(successCallback, failureCallback) {
		this.fetch(Config.API_URL + ApiPath.GROUP_AVAILABLE, {
			method: 'GET'
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	getGroup(id, successCallback, failureCallback) {
		this.fetch(Config.API_URL + ApiPath.GROUP + "/" + id, {
			method: 'GET'
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	updateGroup(aircraft, successCallback, failureCallback) {
		const method = aircraft.id === 'new' ? 'POST' : 'PUT'
		this.fetch(Config.API_URL + ApiPath.GROUP, {
			method: method,
			body: JSON.stringify(aircraft)
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	deleteGroup(id, successCallback, failureCallback) {
		this.fetch(Config.API_URL + ApiPath.GROUP, {
			method: 'DELETE',
			body: JSON.stringify({id: id})
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	getMemberships(groupid, successCallback, failureCallback) {
		this.fetch(Config.API_URL + "/api/group/" + groupid + "/membership", {
			method: 'GET',
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	inviteMember(groupid, id, successCallback, failureCallback) {
		this.fetch(Config.API_URL + ApiPath.GROUP_INVITE, {
			method: 'POST',
			body: JSON.stringify({id: groupid, invitee: id})
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

}

const instance = new GroupService()
Object.freeze(instance)
export default instance
