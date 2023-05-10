import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileEditor {
    private String fileName;
    private String fileContent;

    public void openFile(String fileName) {
        this.fileName = fileName;
        Scanner scanner = new Scanner(fileName);
        StringBuilder contentBuilder = new StringBuilder();

        while (scanner.hasNextLine()) {
            contentBuilder.append(scanner.nextLine());
            contentBuilder.append("\n");
        }

        scanner.close();
        fileContent = contentBuilder.toString();
        System.out.println("File opened successfully.");
    }

    public void saveFile() {
        if (fileName == null || fileContent == null) {
            System.out.println("No file open. Use 'Save As' instead.");
            return;
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(fileContent);
            writer.close();
            System.out.println("File saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving the file: " + e.getMessage());
        }
    }

    public void saveFileAs(String newFileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName));
            writer.write(fileContent);
            writer.close();
            fileName = newFileName;
            System.out.println("File saved as " + newFileName + " successfully.");
        } catch (IOException e) {
            System.out.println("Error saving the file: " + e.getMessage());
        }
    }
}