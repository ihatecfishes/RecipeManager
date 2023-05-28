import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;

public class Recipe {
	private String name;
	private String description;
	private String content;
	private String notes;
	private float price;
	private float time;
	private HashMap<Ingredient, Unit> ingredients;
	private ArrayList<String> images;
	private ArrayList<String> tags;
	private EnumMap<Nutrition, Unit> nutrition;

	public Recipe(String name) {
		this.name = name;
		this.ingredients = new HashMap<>();
		this.images = new ArrayList<>();
		this.tags = new ArrayList<>();
		this.nutrition = new EnumMap<>(Nutrition.class);
	}


	public String getBody() {
		return content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

	public void setIngredient(Ingredient ingredient, Unit unit) {
		ingredients.put(ingredient, unit);
	}

	public void setIngredient(Ingredient ingredient, float value) {
		// Implement your logic here
	}

	public void removeIngredient(Ingredient ingredient) {
		ingredients.remove(ingredient);
	}

	public void addImage(String path) {
		images.add(path);
	}

	public void removeImage(String path) {
		images.remove(path);
	}

	public void addTag(String tag) {
		tags.add(tag);
	}

	public void removeTag(String tag) {
		tags.remove(tag);
	}

	public void setNutrition(Nutrition nutrition, Unit unit) {
		this.nutrition.put(nutrition, unit);
	}

	public void setNutrition(Nutrition nutrition, float value) {
		// Implement your logic here
	}

	public void removeNutrition(Nutrition nutrition) {
		this.nutrition.remove(nutrition);
	}

	public float calculatePrice() {
		// Implement your logic here
		return 0;
	}

	public EnumMap<Nutrition, Unit> calculateNutrition() {
		// Implement your logic here
		return null;
	}

	@Override
	public String toString() {
		return getName();
	}
}
