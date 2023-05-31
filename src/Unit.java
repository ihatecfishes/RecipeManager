class Unit {
	Measurement measurement;
	float value;

	public Unit(Measurement measurement, float value) {
		this(measurement);
		this.value = value;
	}

	public Unit(Measurement measurement) {
		this.measurement = measurement;
	}

	public Measurement getMeasurement() {
		return measurement;
	}

	void setMeasurement(Measurement measurement) {

		this.measurement = measurement;
	}

	public float getValue() {
		return value;
	}

	void setValue(float value) {

		this.value = value;
	}

	public float getValueMultiply(float u) {
		value = value * u;
		return value;
	}

}