import '../css/app.css'
import '../css/notice.css'

import React from 'react';
import Icons from "../util/Icons";

export default function Notice(props) {
	const visible = props.messages.length > 0
	return visible ? <NoticeElement messages={props.messages} priority={props.priority} onClick={props.clearMessages}/> : null;
}

function NoticeElement(props) {
	const priorityClass = 'notice-' + props.priority;

	return (
		<div className={'notice ' + priorityClass} hidden='hidden'>
			<div className='notice-messages'>
				{props.messages.map((message, index) => {
					return (<div key={index} className='notice-message'>{message}</div>)
				})}
			</div>
			{!!props.onClick ? <CloseButton onClick={props.onClick}/> : null}
		</div>
	)
}

function CloseButton(props) {
	return (
		<button className='notice-close' onClick={props.onClick}>{Icons.CLOSE}</button>
	)
}
