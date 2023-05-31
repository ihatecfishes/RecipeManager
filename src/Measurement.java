abstract class Measurement {
	public abstract float convert(float value);
	public abstract float revert(float value);
	public abstract String getName();
	public abstract String getAbbreviation();
	public abstract MeasurementTypes getType();
}

class Kilogram extends Measurement {
	@Override
	public float convert(float value) {
		return value;
	}

	@Override
	public float revert(float value) {
		return value;
	}

	@Override
	public String getName() {
		return "kilogram";
	}

	@Override
	public String getAbbreviation() {
		return "kg";
	}

	@Override
	public MeasurementTypes getType() {
		return MeasurementTypes.Mass;
	}
}

class MetersSquared extends Measurement {

	@Override
	public float convert(float value) {
		return value;
	}

	@Override
	public float revert(float value) {
		return value;
	}

	@Override
	public String getName() {
		return "meters squared";
	}

	@Override
	public String getAbbreviation() {
		return "m2";
	}

	@Override
	public MeasurementTypes getType() {
		return MeasurementTypes.Volume;
	}
}

class Quantity extends Measurement {

	@Override
	public float convert(float value) {
		return value;
	}

	@Override
	public float revert(float value) {
		return value;
	}

	@Override
	public String getName() {
		return "quantity";
	}

	@Override
	public String getAbbreviation() {
		return "qty";
	}

	@Override
	public MeasurementTypes getType() {
		return MeasurementTypes.Quantity;
	}
}

class Pieces extends Measurement {

	@Override
	public float convert(float value) {
		return value;
	}

	@Override
	public float revert(float value) {
		return value;
	}

	@Override
	public String getName() {
		return "pieces";
	}

	@Override
	public String getAbbreviation() {
		return "pcs";
	}

	@Override
	public MeasurementTypes getType() {
		return MeasurementTypes.Quantity;
	}
}