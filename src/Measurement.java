abstract class Measurement {
	public abstract float convert(float value);
	public abstract float revert(float value);
	public abstract String getName();
	public abstract String getAbbreviation();
	public abstract MeasurementType getType();

	@Override
	public String toString() {
		return getAbbreviation();
	}
}

class Measurements {
	public static Measurement[] mass = new Measurement[] {
			new Kilogram(),
			new Gram()
	};
	public static Measurement[] volume = new Measurement[] {
			new MetersSquared(),
	};
	public static Measurement[] quantity = new Measurement[] {
			new Quantity(),
			new Pieces(),
	};
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
	public MeasurementType getType() {
		return MeasurementType.Mass;
	}
}

class Gram extends Measurement {

	@Override
	public float convert(float value) {
		return value * 1000;
	}

	@Override
	public float revert(float value) {
		return value / 1000;
	}

	@Override
	public String getName() {
		return "gram";
	}

	@Override
	public String getAbbreviation() {
		return "g";
	}

	@Override
	public MeasurementType getType() {
		return MeasurementType.Mass;
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
	public MeasurementType getType() {
		return MeasurementType.Volume;
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
	public MeasurementType getType() {
		return MeasurementType.Quantity;
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
	public MeasurementType getType() {
		return MeasurementType.Quantity;
	}
}