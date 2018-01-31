import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui.fxml"));
        //loader.setControllerFactory(control -> new mainControl(new freqModel()));

        primaryStage.setTitle("DSS");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.getScene().getStylesheets().add("ui.css");
        primaryStage.show();

    }
}
