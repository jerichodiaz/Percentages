package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Logs {
    private final StringProperty name;
    private final StringProperty productName;
    private final IntegerProperty index;
    private final IntegerProperty quantity;

    public Logs(int index, String name, String productName, int quantity){
        this.index = new SimpleIntegerProperty(index);
        this.name = new SimpleStringProperty(name);
        this.productName = new SimpleStringProperty(productName);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public void setName(String name){
        this.name.set(name);
    }
    public void setProductName(String productName){
        this.productName.set(productName);
    }
    public void setIndex(int index){
        this.index.set(index);
    }
    public void setQuantity(int quantity){
        this.quantity.set(quantity);
    }
    public String getName(){
        return name.get();
    }
    public String getProductName(){
        return productName.get();
    }
    public int getIndex(){
        return index.get();
    }
    public int getQuantity(){
        return quantity.get();
    }
    public StringProperty nameProperty(){
        return name;
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public IntegerProperty indexProperty() {
        return index;
    }

    public StringProperty productNameProperty() {
        return productName;
    }
}
