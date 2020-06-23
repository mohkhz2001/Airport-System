import Model.Airplane;
import Model.AirplaneRepository;
import Model.Flight;
import Model.FlightRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane loader = FXMLLoader.load(getClass().getResource("View/Login.fxml"));

        primaryStage.setScene(new Scene(loader));
        primaryStage.setTitle("sign in");
        primaryStage.getIcons().add(new Image("file:Icons/login.png"));
        primaryStage.setResizable(false);

        primaryStage.show();

    }
}
