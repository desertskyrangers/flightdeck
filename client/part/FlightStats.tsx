import Times from "../util/Times";
import React from "react";

export function FlightStatsHeader() {
	return (
		<tr>
			<td className='dashboard-header'>Flights</td>
			<td rowSpan={2}>
				<div className='v-separator'/>
			</td>
			<td className='dashboard-header'>Flight Time</td>
		</tr>
	)
}

export function FlightStats(props) {
	return (
		<tr>
			<td className='page-metric'>{props.count}</td>
			<td className='page-metric'>{Times.toSummaryFlightTime(props.time)}</td>
		</tr>
	)
}

