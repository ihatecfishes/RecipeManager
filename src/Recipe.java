import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;

/**
 * Recipe
 */
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
	}

	public Recipe() { }

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

	}

	public void removeNutrition(Nutrition nutrition) {
		this.nutrition.remove(nutrition);
	}

	public float calculatePrice() {
		return 0;
	}

	public EnumMap<Nutrition, Unit> calculateNutrition() {
		return null;
	}

	@Override
	public String toString() {
		return getName();
	}
}