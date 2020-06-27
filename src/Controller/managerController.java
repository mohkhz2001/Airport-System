package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class managerController  implements Initializable {

    private String ID;

    @FXML
    HBox hbox;
    @FXML
    SplitPane split;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void employee(String ID){
        setID(ID);
        Button profileBTN = new Button();
        Button flightManagerBTN = new Button();
        Button exitBTN = new Button();

        profileBTNSetup(profileBTN);
        profileBTNAction(profileBTN);

        flightManagerSetup(flightManagerBTN);
        flightManagerBTNAction(flightManagerBTN);


        exitBTNSetup(exitBTN);
        exitBTNAction(exitBTN);

        hbox.getChildren().addAll(profileBTN  , flightManagerBTN , exitBTN);
    }

    private void exitBTNAction(Button exitBTN){

        exitBTN.setOnMouseClicked(event -> {
            ((Stage)exitBTN.getScene().getWindow()).close();
        });

        exitBTN.setOnMouseEntered(event -> {
            exitBTN.setStyle("-fx-background-color: red ");
        });

        exitBTN.setOnMouseExited(event -> {
            exitBTN.setStyle("-fx-background-color: transparent");
        });
    }

    private void exitBTNSetup(Button exitBTN){

        exitBTN.setMinHeight(80);
        exitBTN.setMinWidth(60);
        exitBTN.setStyle("-fx-background-color: transparent");
        exitBTN.setText("exit");


    }

    private void flightManagerSetup(Button flightManagerBTN){
        flightManagerBTN.setMinWidth(62);
        flightManagerBTN.setMinHeight(80);
        flightManagerBTN.setStyle("-fx-background-color: transparent");
        flightManagerBTN.setText("flight");
    }

    private void flightManagerBTNAction(Button flightManagerBTN){

        flightManagerBTN.setOnMouseClicked(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FlightManager.fxml"));

            try {
                loader.load();
                split.getItems().set(1, loader.getRoot());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        flightManagerBTN.setOnMouseEntered(event -> {
            flightManagerBTN.setStyle("-fx-background-color: #21d9d9 ");
        });

        flightManagerBTN.setOnMouseExited(event -> {
            flightManagerBTN.setStyle("-fx-background-color: transparent");
        });
    }

    private void profileBTNSetup(Button profileBTN){
        profileBTN.setMinWidth(62);
        profileBTN.setMinHeight(80);
        profileBTN.setStyle("-fx-background-color: transparent");
        profileBTN.setText("profile");
    }

    private void profileBTNAction(Button profileBTN){

        profileBTN.setOnMouseClicked(event -> {
            System.out.println("profileBTN");
        });

        profileBTN.setOnMouseEntered(event -> {
            profileBTN.setStyle("-fx-background-color: #21d9d9 ");
        });

        profileBTN.setOnMouseExited(event -> {
            profileBTN.setStyle("-fx-background-color: transparent");
        });
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
