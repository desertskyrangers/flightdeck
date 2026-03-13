import React, {useCallback, useEffect, useState} from "react";
import Times from "../util/Times";

export default function Ago(props) {
	const [now, setNow] = useState(-1)

	const updateNow = useCallback(() => {
		setNow(new Date().getTime())
	}, [])

	useEffect(() => {
		updateNow()
		const interval = setInterval(updateNow, 1000)
		return () => clearInterval(interval)
	}, [updateNow])

	return (
		<span>{Times.toAgo(props.timestamp, now)}</span>
	)
}
