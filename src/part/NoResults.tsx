import Icons from "../util/Icons";
import React from "react";

export default function NoResults(props) {

	const message = props.message || 'no results'

	return (
		<div className='page-result'>{Icons.NO_RESULT} {message}</div>
	)

}
