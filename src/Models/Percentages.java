package Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Percentages {
    private final StringProperty productName;
    private final StringProperty percentage;

    public Percentages(String productName, String percentage){
        this.productName = new SimpleStringProperty(productName);
        this.percentage = new SimpleStringProperty(percentage);
    }

    public String getProductName(){
        return productName.get();
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
