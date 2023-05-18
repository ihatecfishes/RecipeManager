import java.util.ArrayList;
import java.util.EnumMap;

public class Ingredient {
	private String name;
	private String description;
	private String notes;
	private float price;
	private Measurement measurement;
	private ArrayList<String> images;
	private ArrayList<String> tags;
	private EnumMap<Nutrition, Unit> nutritions;

	public Ingredient(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	public void addImage(String path) {
		images.add(path);
	}

	public void removeImage(String path) {
		images.remove(path);
	}

	public void setNutrition(Nutrition nutrition, Unit unit) {
		nutritions.put(nutrition, unit);
	}

	public void addTag(String tag) {
		tags.add(tag);
	}

	public void removeTag(String tag) {
		tags.remove(tag);
	}

	public void setNutrition(Nutrition nutrition, float value) {
		// TODO: add
	}

	public void removeNutrition(Nutrition nutrition) {
		nutritions.remove(nutrition);
	}
}
