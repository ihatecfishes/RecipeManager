import  java.util.*;
enum MeasurementTypes {
    Mass,
    Volume,
    Quantity
}

class Measurement{
    String name;
    String abbreviation;
    MeasurementTypes type;

    public Measurement(String name, String abbreviation, MeasurementTypes type){
        this.name = name;
        this.abbreviation = abbreviation;
        this.type = type;
    }
    public String getName(){
        return name;
    }
    public String getType(){
        return type;


}

class Unit extends Measurement{
    Measurement measurement;
    float value;
    public Unit(Measurement measurement,float value){
        this(measurement);
        this.value = value;
    }
    public Unit(Measurement measurement){
        this.measurement = measurement;
    }
    public Measurement getMeasurement(){
        return measurement;
    }
    void setMeasurement(Measurement measurement) {

        this.measurement = measurement;
    }

    public float getvalue(){
        return value;
    }
    void setValue(float value) {

        this.value = value;
    }

    public float getValueMultiply(float u){
        value = value*u;
        return value;
    }


}
}