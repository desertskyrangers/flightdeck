class Dates {

	isValidDate(date) {
		return !isNaN(date.getTime());
	}

	isoDate(date) {
		let YYYY = date.getFullYear();
		let MM = date.getMonth() + 1; // getMonth() is zero-based
		let DD = date.getDate();

		return [YYYY, (MM > 9 ? '' : '0') + MM, (DD > 9 ? '' : '0') + DD].join('-');
	};

	isoDateHourMin(date) {
		let YYYY = date.getFullYear();
		let MM = date.getMonth() + 1; // getMonth() is zero-based
		let DD = date.getDate();
		let hh = date.getHours();
		let mm = date.getMinutes();

		return [YYYY, (MM > 9 ? '' : '0') + MM, (DD > 9 ? '' : '0') + DD].join('-') + 'T' + [(hh > 9 ? '' : '0') + hh, (mm > 9 ? '' : '0') + mm].join(':');
	};

	humanDateHourMin(date) {
		let YYYY = date.getFullYear();
		let MM = date.getMonth() + 1; // getMonth() is zero-based
		let DD = date.getDate();
		let hh = date.getHours();
		let mm = date.getMinutes();

		return [YYYY, (MM > 9 ? '' : '0') + MM, (DD > 9 ? '' : '0') + DD].join('-') + ' ' + [(hh > 9 ? '' : '0') + hh, (mm > 9 ? '' : '0') + mm].join(':');
	};


}

const instance = new Dates()
Object.freeze(instance)
export default instance
