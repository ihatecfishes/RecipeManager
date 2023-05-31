class Unit {
	private String name;
	private Measurement measurement;
	private float value;

	public Unit(String name, Measurement measurement, float value) {
		this.name = name;
		this.measurement = measurement;
		this.value = value;
	}

	public Unit(Measurement measurement) {
		this.measurement = measurement;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
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