import Models.Logs;
import Models.Percentages;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML private TextField customerName;
    @FXML private ListView productList, customerList;
    @FXML private TableView productLogs, productPercentages;
    @FXML private CheckMenuItem deleteOnEnter;
    private final String letters = "ABCDEFGHIJKLMNOPQRSTUVwXYZ";

    private TableController controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new TableController();
        initTables();
        for(int i = 0; i < 5; i ++)
            addProduct();

        customerList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                controller.changeCustomerPercentages(newValue.toString());
            }
        });
    }

    @FXML private void onEnter(ActionEvent ae){
        String name = customerName.getText();
        name = name.isEmpty()?"Customer":name;
        controller.addProduct(name);
        if(deleteOnEnter.isSelected())
            customerName.clear();
    }
    @FXML private void addProduct(){
        char letter = letters.charAt(controller.PRODUCT_LIST.size());
        controller.PRODUCT_LIST.add(letter+"");
    }
    @FXML private void removeProduct(){
        if(controller.PRODUCT_LIST.size()>0){
            controller.PRODUCT_LIST.remove(controller.PRODUCT_LIST.size()-1);
        }
    }
    @FXML private void onClose(){
        System.exit(0);
    }

    private void initTables(){

        productList.setItems(controller.PRODUCT_LIST);
        customerList.setItems(controller.CUSTOMER_LIST);

        //productLogs
        TableColumn<Logs, String> logsIndex = new TableColumn<>("#");
        logsIndex.setCellValueFactory(new PropertyValueFactory<Logs, String>("index"));
        TableColumn<Logs, String> logsCustomerName = new TableColumn<>("Customer Name");
        logsCustomerName.setCellValueFactory(new PropertyValueFactory<Logs, String>("name"));
        TableColumn<Logs, String> logsProductName = new TableColumn<>("Product Name");
        logsProductName.setCellValueFactory(new PropertyValueFactory<Logs, String>("productName"));
        TableColumn<Logs, String> logsQuantity = new TableColumn<>("Quantity");
        logsQuantity.setCellValueFactory(new PropertyValueFactory<Logs, String>("quantity"));

        //productPercentages
        TableColumn<Percentages, String> percName = new TableColumn<>("Product Name");
        percName.setCellValueFactory(new PropertyValueFactory<Percentages, String>("productName"));
        TableColumn<Percentages, String> percPercentage = new TableColumn<>("%");
        percPercentage.setCellValueFactory(new PropertyValueFactory<Percentages, String>("percentage"));

        //binding
        productLogs.getColumns().addAll(logsIndex, logsCustomerName, logsProductName, logsQuantity);
        productPercentages.getColumns().addAll(percName, percPercentage);
        productLogs.setItems(controller.PRODUCT_LOGS);
        productPercentages.setItems(controller.PRODUCT_PERCENTAGES);
    }


}
