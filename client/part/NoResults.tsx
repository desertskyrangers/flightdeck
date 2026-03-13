import Icon from "../util/Icon.tsx";
import React from "react";

export default function NoResults(props) {

	const message = props.message || 'no results'

	return (
		<div className='page-result'>{Icon.NO_RESULT} {message}</div>
	)

}
