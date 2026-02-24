import React, {useCallback, useEffect, useState} from "react";
import MembershipIcon from "./MembershipStatusIcon";
import Icons from "../util/Icons";
import MembershipService from "../api/MembershipService";

export function MembershipUser(props) {
	//const navigate = useNavigate();

	const [acceptAction, setAcceptAction] = useState(false)
	const [revokeAction, setRevokeAction] = useState(false)
	const [cancelAction, setCancelAction] = useState(false)

	function doClick() {
		//if (props.membership.status === 'owner') navigate(AppPath.GROUP + "/" + props.membership.group.id)
	}

	// const isOwner = useCallback(() => {
	// 	return props.membership.status === 'owner'
	// }, [props.membership.status])

	const isAccepted = useCallback(() => {
		return props.membership.status === 'accepted'
	}, [props.membership.status])

	const isInvited = useCallback(() => {
		return props.membership.status === 'invited'
	}, [props.membership.status])

	const isRequested = useCallback(() => {
		return props.membership.status === 'requested'
	}, [props.membership.status])

	const isRevoked = useCallback(() => {
		return props.membership.status === 'revoked'
	}, [props.membership.status])

	function doAccept() {
		MembershipService.acceptMembership(props.membership.id, (result) => {
			props.onMemberUpdate()
		}, (failure) => {

		})
	}

	function doCancel() {
		MembershipService.cancelMembership(props.membership.id, (result) => {
			props.onMemberUpdate()
		}, (failure) => {

		})
	}

	function doRevoke() {
		MembershipService.revokeMembership(props.membership.id, (result) => {
			props.onMemberUpdate()
		}, (failure) => {

		})
	}

	useEffect(() => {
		setAcceptAction(isRequested() || isRevoked())
		setCancelAction(isInvited())
		setRevokeAction(isAccepted() || isRequested())
	}, [isRequested, isRevoked, isInvited, isAccepted])

	return (
		<div className='hbox'>
			<div className='page-row' onClick={doClick}>
				<MembershipIcon status={props.membership.status}/>
				{/*&nbsp;{Icons.fromGroupType(props.membership.group.type)}*/}
				<span className='page-text'>{props.membership.user.name}</span>
			</div>
			{acceptAction ? <button className='icon' onClick={doAccept}>{Icons.ACCEPT}</button> : null}
			{cancelAction ? <button className='icon' onClick={doCancel}>{Icons.CANCEL}</button> : null}
			{revokeAction ? <button className='icon' onClick={doRevoke}>{Icons.REVOKE}</button> : null}
			{props.actionIcon ? <button className='icon' onClick={props.onAction}>{props.actionIcon}</button> : null}
		</div>
	)
}
