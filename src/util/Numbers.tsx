class Numbers {

	decimalToHex(d: number, padding: number) {
		let hex = Number(d).toString(16);
		padding = typeof (padding) === "undefined" || padding === null ? 1 : padding;

		while (hex.length < padding) {
			hex = "0" + hex;
		}

		return hex;
	}

}

const instance = new Numbers()
Object.freeze(instance)
export default instance
