class Times {

	toDetailedFlightTime(seconds) {
		return this.toHourMinSec(seconds)
	}

	toSummaryFlightTime(seconds) {
		return this.toHourMin(seconds)
	}

	toFlightTime(seconds) {
		return this.toMinSec(seconds)
	}

	toHourMinSec(seconds) {
		let duration = seconds

		let ss = duration % 60
		duration = Math.floor(duration / 60)
		let mm = duration % 60
		duration = Math.floor(duration / 60)
		let hh = duration

		return String(hh).padStart(2, '0') + ":" + String(mm).padStart(2, '0') + ":" + String(ss).padStart(2, '0') + "s"
	}

	toHourMin(seconds) {
		let duration = Math.floor(seconds / 60)

		let mm = duration % 60
		duration = Math.floor(duration / 60)
		let hh = duration

		return String(hh).padStart(2, '0') + ":" + String(mm).padStart(2, '0') +"m"
	}

	toMinSec(seconds) {
		let duration = seconds

		let ss = duration % 60
		duration = Math.floor(duration / 60)
		let mm = duration

		return String(mm).padStart(2, '0') + ":" + String(ss).padStart(2, '0') + "s"
	}

	toAgo(timestamp, now) {
		if (timestamp < 0) return "Never"
		if (!!!now) now = new Date().getTime()
		return this.toAgoAbbrevFromSeconds((now - timestamp) / 1000)
	}

	toAgoAbbrevFromSeconds(seconds) {
		if (seconds < 0) return "Never"

		let duration = Math.floor(seconds / 60)
		if (duration < 1) return "<1m ago"
		if (duration < 60) return duration + "m ago"

		duration = Math.floor(duration / 60)
		if (duration < 24) return duration + "h ago"

		duration = Math.floor(duration / 24)
		if (duration < 7) return duration + "d ago"

		duration = Math.floor(duration / 7)
		if (duration < 52) return duration + "w ago"

		return duration + "y ago"
	}

}

const instance = new Times()
Object.freeze(instance)
export default instance
