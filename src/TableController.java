import Models.Logs;
import Models.Percentages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

public class TableController {
    private final int TOTAL_QUANTITY = 5;
    public final ObservableList<String> PRODUCT_LIST = FXCollections.observableArrayList();
    public final ObservableList<String> CUSTOMER_LIST = FXCollections.observableArrayList();
    public final ObservableList<Logs> PRODUCT_LOGS = FXCollections.observableArrayList();
    public final ObservableList<Percentages> PRODUCT_PERCENTAGES = FXCollections.observableArrayList();
    private final DecimalFormat df = new DecimalFormat("#.##");

    public void addProduct(String customerName){

        boolean repeat = false;
        for(int i = 0; i < CUSTOMER_LIST.size(); i++){
            if(CUSTOMER_LIST.get(i).equals(customerName))
                repeat = true;
        }
        if(!repeat) {
            CUSTOMER_LIST.add(customerName);
        }

        int index = (int) (Math.random()*PRODUCT_LIST.size());
        int quantity = (int) (Math.random()*TOTAL_QUANTITY)+1;

        PRODUCT_LOGS.add(new Logs(PRODUCT_LOGS.size()+1, customerName, PRODUCT_LIST.get(index), quantity));
    }

    public void changeCustomerPercentages(String customerName) {
        PRODUCT_PERCENTAGES.removeAll(PRODUCT_PERCENTAGES);
        double totalQuantity = 0;
        ArrayList<Logs> customerLogs = new ArrayList<>();
        for (int i = 0; i < PRODUCT_LOGS.size(); i++) {
            if (PRODUCT_LOGS.get(i).getName().equals(customerName)) {
                customerLogs.add(PRODUCT_LOGS.get(i));
                totalQuantity += PRODUCT_LOGS.get(i).getQuantity();
            }
        }
        for (int i = 0; i < PRODUCT_LIST.size(); i++) {
            String productName = PRODUCT_LIST.get(i);
            double percent = 0;
            double total = 0;
            for (int j = 0; j < customerLogs.size(); j++) {
                Logs customerLog = customerLogs.get(j);
                if (customerLog.getProductName().equals(productName)) {
                    total += customerLogs.get(j).getQuantity();
                }
            }
            percent = total / totalQuantity * 100;
            PRODUCT_PERCENTAGES.add(new Percentages(productName, df.format(percent) + "%"));
        }
    }
    public void reset(){
        PRODUCT_LIST.clear();
        PRODUCT_LOGS.clear();
        PRODUCT_PERCENTAGES.clear();
        CUSTOMER_LIST.clear();
    }

}
