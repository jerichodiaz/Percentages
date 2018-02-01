package Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Percentages {
    private final StringProperty productName;
    private final StringProperty percentage;
    private double dpercentage;

    public Percentages(String productName, String percentage){
        this.productName = new SimpleStringProperty(productName);
        if(percentage.equals("NaN%"))
            percentage="0%";
        this.percentage = new SimpleStringProperty(percentage);
        System.out.println(percentage);
        this.dpercentage = Double.parseDouble(percentage.substring(0, percentage.length()-1));
    }

    public String getProductName(){
        return productName.get();
    }
    public double getDpercentage(){
        return dpercentage;
    }
    public void setDpercentage(double p){
        dpercentage=p;
    }
    public String getPercentage(){
        return percentage.get();
    }
    public void setProductName(String productName){
        this.productName.set(productName);
    }
    public void setPercentage(String percentage){
        this.percentage.set(percentage);
    }

    public StringProperty productNameProperty() {
        return productName;
    }

    public StringProperty percentageProperty() {
        return percentage;
    }
}
