import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class RecipeSaver {
    public static void saveRecipe(String filePath, Map<String, Integer> ingredients) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Recipe:\n");
            for (Map.Entry<String, Integer> entry : ingredients.entrySet()) {
                String ingredient = entry.getKey();
                int quantity = entry.getValue();
                writer.write("- " + ingredient + ": " + quantity + "\n");
            }
            writer.flush();
            System.out.println("Recipe saved successfully!");
        } catch (IOException e) {
            System.out.println("Failed to save recipe!");
        }
    }
}
