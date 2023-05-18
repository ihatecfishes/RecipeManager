class Measurement {
	String name;
	String abbreviation;
	MeasurementTypes type;

	public Measurement(String name, String abbreviation, MeasurementTypes type) {
		this.name = name;
		this.abbreviation = abbreviation;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public MeasurementTypes getType(){
        return type;
	}
}