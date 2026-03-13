import Icon from "../util/Icon.tsx";

export default function MembershipIcon(props) {

	function getIcon(key) {
		switch (key) {
			case'owner':
				return Icon.OWNER
			case'accepted':
				return Icon.MEMBER
			case'invited':
				return Icon.ENVELOPE
			case'requested':
				return Icon.ENVELOPE
			case'revoked':
				return Icon.CANCEL
			default:
				return Icon.UNKNOWN
		}
	}

	function getText(key) {
		switch (key) {
			case 'owner':
				return 'Owner'
			case 'accepted':
				return 'Member'
			case 'invited':
				return 'Invited'
			case 'requested':
				return 'Requested'
			case 'revoked':
				return 'Revoked'
			default:
				return key
		}
	}

	return (
		<span className={'tooltip membership-status ' + props.status}>
			<span className={'tooltiptext membership-status ' + props.status}>{getText(props.status)}</span>
			{getIcon(props.status)}
		</span>
	)

}
