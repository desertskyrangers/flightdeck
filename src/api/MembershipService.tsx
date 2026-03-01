import ApiService from "./ApiService";
import Config from "../AppConfig";
import ApiPath from "./ApiPath";

class MembershipService extends ApiService {

	requestMembership(userid, groupid, type, successCallback, failureCallback) {
		this.fetch(Config.API_URL + ApiPath.USER_MEMBERSHIP, {
			method: 'PUT',
			body: JSON.stringify({
				userid: userid,
				groupid: groupid,
				status: type
			})
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	acceptMembership(membershipId, successCallback, failureCallback) {
		this.fetch(Config.API_URL + ApiPath.MEMBERSHIP, {
			method: 'PUT',
			body: JSON.stringify({
				id: membershipId,
				status: 'accepted'
			})
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	revokeMembership(membershipId, successCallback, failureCallback) {
		this.fetch(Config.API_URL + ApiPath.MEMBERSHIP, {
			method: 'PUT',
			body: JSON.stringify({
				id: membershipId,
				status: 'revoked'
			})
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

	cancelMembership(membershipId, successCallback, failureCallback) {
		this.fetch(Config.API_URL + ApiPath.MEMBERSHIP, {
			method: 'DELETE',
			body: JSON.stringify({
				id: membershipId
			})
		}).then((response) => {
			successCallback(response)
		}).catch((error) => {
			failureCallback(error)
		})
	}

}

const instance = new MembershipService()
Object.freeze(instance)
export default instance
