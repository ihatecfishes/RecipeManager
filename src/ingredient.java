import java.util.ArrayList;
import java.util.Scanner;

public class ingredient {
    
    private String nameOfIngredient;
    private float numMeasure;
    private String measurement;
    private int numCalsPerMeasure;
    private double totalCalories;
    
    public ingredient() {
        
        this.nameOfIngredient = "";
        this.numMeasure = 0;
        this.measurement = "";
        this.numCalsPerMeasure = 0;
        this.totalCalories = 0;
    }

    public String getNameOfIngredient() {
        return nameOfIngredient;
    }
    
    public void setNameOfIngredient(String nameOfIngredient) {
        this.nameOfIngredient = nameOfIngredient;
    }
    
    public float getNumMeasure() {
        return numMeasure;
    }
    
    public void setNumMeasure(float numMeasure) {
        this.numMeasure = numMeasure;
    }
    
    public String getMeasurement() {
        return measurement;
    }
    
    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
    
    public int getNumCalsPerMeasure() {
        return numCalsPerMeasure;
    }
    
    public void setNumCalsPerMeasure(int numCalsPerMeasure) {
        this.numCalsPerMeasure = numCalsPerMeasure;
    }
    
    public double getTotalCalories() {
        return totalCalories;
    }
    
    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }
    
    public ingredient(String nameOfIngredient, float numMeasure, String measurement,
                      int numCalsPerMeasure, double totalCalories) {
        
        this.nameOfIngredient = nameOfIngredient;
        this.numMeasure = numMeasure;
        this.measurement = measurement;
        this.numCalsPerMeasure = numCalsPerMeasure;
        this.totalCalories = totalCalories;
    }
    
    public ingredient addIngredient(String tempNameOfIngredient) {
        
        Scanner ingScnr = new Scanner(System.in);
        String userReply = "";
        boolean loop = true;
        
        System.out.print("Please enter the name of the ingredient: ");

        nameOfIngredient = ingScnr.nextLine();
        
        System.out.print("Please enter the measurement type: ");
        measurement = ingScnr.nextLine().toLowerCase();
        
        System.out.print("Please enter the number of " + measurement
                         + "(s) of " + nameOfIngredient + " we'll need: ");
        numMeasure = ingScnr.nextFloat();
        
        System.out.print("Please enter the number of calories per "
                         + measurement + ": ");
        numCalsPerMeasure = ingScnr.nextInt();
        
        totalCalories = (double)numMeasure * (double)numCalsPerMeasure;
        
        ingredient tempIngredients = new ingredient(nameOfIngredient,
                                                    numMeasure, measurement, numCalsPerMeasure,
                                                    totalCalories);
        return tempIngredients;
    }
    
    @Override
    public String toString() {
        return (nameOfIngredient + " " + numMeasure + " " + measurement
                + "(s) " + numCalsPerMeasure + " calories per " + measurement + " "
                + totalCalories + " total calories ");
    }
}