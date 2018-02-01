import Models.Logs;
import Models.Percentages;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML private TextField customerName;
    @FXML private ListView productList, customerList;
    @FXML private TableView productLogs, productPercentages;
    @FXML private CheckMenuItem deleteOnEnter;
    @FXML private Label data;
    private final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private TableController controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new TableController();
        initTables();

        customerList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(newValue!=null)
                controller.changeCustomerPercentages(newValue.toString());
            }
        });

        for(int i = 0; i < 5; i ++)
            addProduct();
    }

    @FXML private void onEnter(ActionEvent ae){
        if(controller.PRODUCT_LIST.size()>0) {
            String name = customerName.getText();
            name = name.isEmpty() ? "Customer" : name;
            controller.addProduct(name);
            if (deleteOnEnter.isSelected())
                customerName.clear();
            updatePercentages();
        }
    }
    @FXML private void addProduct(){
        if(controller.PRODUCT_LIST.size()!=26) {
            String letter = letters.charAt(controller.PRODUCT_LIST.size()) + "";
            controller.PRODUCT_LIST.add(letter + "");
            updatePercentages();
        }
    }
    @FXML private void onReset(){
        controller.reset();
        data.setText("");
        for(int i = 0; i < 5; i++)
            addProduct();
    }
    @FXML private void onClose(){
        System.exit(0);
    }

    private void updatePercentages(){
        try {
            if (!customerList.getSelectionModel().getSelectedItem().toString().isEmpty())
                controller.changeCustomerPercentages(customerList.getSelectionModel().getSelectedItem().toString());
            data.setText(controller.update());
        }
        catch (NullPointerException npe){
            controller.changeTotalPercentages();
            data.setText(controller.update());
        }
    }

    private void initTables(){

        productList.setItems(controller.PRODUCT_LIST);
        customerList.setItems(controller.CUSTOMER_LIST);

        //productLogs
        TableColumn<Logs, String> logsIndex = new TableColumn<>("#");
        logsIndex.setCellValueFactory(new PropertyValueFactory<Logs, String>("index"));
        logsIndex.setPrefWidth(50);
        TableColumn<Logs, String> logsCustomerName = new TableColumn<>("Customer Name");
        logsCustomerName.setCellValueFactory(new PropertyValueFactory<Logs, String>("name"));
        logsCustomerName.setPrefWidth(261);
        TableColumn<Logs, String> logsProductName = new TableColumn<>("Product Name");
        logsProductName.setCellValueFactory(new PropertyValueFactory<Logs, String>("productName"));
        logsProductName.setPrefWidth(113);
        TableColumn<Logs, String> logsQuantity = new TableColumn<>("Quantity");
        logsQuantity.setCellValueFactory(new PropertyValueFactory<Logs, String>("quantity"));
        logsQuantity.setPrefWidth(69);

        //productPercentages
        TableColumn<Percentages, String> percName = new TableColumn<>("Product Name");
        percName.setCellValueFactory(new PropertyValueFactory<Percentages, String>("productName"));
        percName.setPrefWidth(105);
        TableColumn<Percentages, String> percPercentage = new TableColumn<>("%");
        percPercentage.setCellValueFactory(new PropertyValueFactory<Percentages, String>("percentage"));
        percPercentage.setPrefWidth(105);

        //binding
        productLogs.getColumns().addAll(logsIndex, logsCustomerName, logsProductName, logsQuantity);
        productPercentages.getColumns().addAll(percName, percPercentage);
        productLogs.setItems(controller.PRODUCT_LOGS);
        productPercentages.setItems(controller.PRODUCT_PERCENTAGES);
    }


}
