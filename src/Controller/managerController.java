package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class managerController implements Initializable {

    private String ID;
    private Button exitBTN;

    @FXML
    HBox hbox;
    @FXML
    SplitPane split;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void employee(String ID) {
        Button profileBTN = new Button();
        Button flightManagerBTN = new Button();
        exitBTN = new Button();

        profileBTNSetup(profileBTN);
        profileBTNAction(profileBTN);

        flightManagerSetup(flightManagerBTN);
        flightManagerBTNAction(flightManagerBTN);

        exitBTNSetup(exitBTN);
        exitBTNAction(exitBTN);

        hbox.getChildren().addAll(profileBTN, flightManagerBTN, exitBTN);

    }

    public void manager(String ID) {
        employee(ID);

        Button employee = new Button("employee");
        employeeBTNAction(employee);
        employeeBTNSetup(employee);

        hbox.getChildren().set(2, employee);
        hbox.getChildren().addAll(exitBTN);

    }

    private void exitBTNAction(Button exitBTN) {

        exitBTN.setOnMouseClicked(event -> {
            ((Stage) exitBTN.getScene().getWindow()).close();
        });
    }

    private void exitBTNSetup(Button exitBTN) {
        exitBTN.getStylesheets().add("/resource/exitBTN.css");
        exitBTN.setGraphic(new ImageView("file:Icons/exit.png"));

    }

    private void flightManagerSetup(Button flightManagerBTN) {
        flightManagerBTN.getStylesheets().add("/resource/menuBTN.css");
        flightManagerBTN.setGraphic(new ImageView("file:Icons/flight manage.png"));
    }

    private void flightManagerBTNAction(Button flightManagerBTN) {

        flightManagerBTN.setOnMouseClicked(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FlightManager.fxml"));

            try {
                loader.load();
                split.getItems().set(1, loader.getRoot());

            } catch (IOException e) {
                e.printStackTrace();
            }

            FlightManagerController flightManagerController = loader.getController();
            flightManagerController.setID(getID());
            flightManagerController.getJob();
            flightManagerController.management();

        });

    }

    private void profileBTNSetup(Button profileBTN) {
        profileBTN.getStylesheets().add("/resource/menuBTN.css");
        profileBTN.setGraphic(new ImageView("file:Icons/profile.png"));
    }

    private void profileBTNAction(Button profileBTN) {

        profileBTN.setOnMouseClicked(event -> {
            System.out.println("profileBTN");
        });
    }

    private void employeeBTNAction(Button employeeBTN) {
        employeeBTN.setOnMouseClicked(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/EmployeeBTN.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            split.getItems().set(1 , loader.getRoot());

        });
    }

    private void employeeBTNSetup(Button employeeBTN) {
        employeeBTN.getStylesheets().add("/resource/menuBTN.css");
//        employeeBTN.setGraphic(new ImageView("file:Icons/exit.png"));

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}