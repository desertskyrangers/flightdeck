import {useState} from "react";

export default function DeleteWithConfirm(props) {

	const [name, setName] = useState('')
	const [canDelete, setCanDelete] = useState(false)

	function doDelete() {
		props.onDelete()
	}

	function onKeyDown(event) {
		if (event.key === 'Enter') document.getElementById('submit-delete').click()
	}

	function updateName(event) {
		setName(event.target.value)
		setCanDelete(event.target.value === props.name)
	}

	return (
		<div className='vbox'>
			WARNING: This action cannot be undone. Please type the {props.entity} to confirm:
			<input id='name' name='name' value={name} type='text' placeholder={props.placeholder} className='page-field' onChange={updateName} onKeyDown={onKeyDown}/>
			<button id='submit-delete' disabled={!canDelete} className='page-action' onClick={doDelete}>Delete</button>
		</div>
	)

}
