class Batteries {

	EXPECTED_LIFETIME_CYCLES = 200;

	private cellVolts = {
		nicd: 1.2,
		lipo: 3.7,
		life: 3.3,
		nimh: 1.2,
		nizn: 1.6,
		lead: 2.0,
	}

	voltsPerCell(type) {
		let volts = instance.cellVolts[type]
		if (!volts) volts = 0
		return volts
	}

}

const instance = new Batteries()
Object.freeze(instance)
export default instance
