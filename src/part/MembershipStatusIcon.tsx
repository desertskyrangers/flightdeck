import Icons from "../util/Icons";

export default function MembershipIcon(props) {

	function getIcon(key) {
		switch (key) {
			case'owner':
				return Icons.OWNER
			case'accepted':
				return Icons.MEMBER
			case'invited':
				return Icons.ENVELOPE
			case'requested':
				return Icons.ENVELOPE
			case'revoked':
				return Icons.CANCEL
			default:
				return Icons.UNKNOWN
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
