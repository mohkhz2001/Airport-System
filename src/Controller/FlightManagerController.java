package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FlightManagerController implements Initializable {

    @FXML
    Button flightsBTN;
    @FXML
    SplitPane split;
    @FXML
    Button airplaneBTN;
    @FXML
    Button NewFlightBTN;


    public void flightsBTN() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FlightsListBTN.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        split.getItems().set(1, loader.getRoot());
    }

    public void flightsBTNEnter() {
        flightsBTN.setStyle("-fx-background-color: #3679c6");
    }

    public void flightsBTNExit() {
        flightsBTN.setStyle("-fx-background-color: transparent ");
    }

    public void airplaneBTN() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/airplaneList.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        split.getItems().set(1, loader.getRoot());
    }

    public void airplaneBTNEnter() {
        airplaneBTN.setStyle("-fx-background-color: #3679c6");
    }

    public void airplaneBTNExit() {
        airplaneBTN.setStyle("-fx-background-color: transparent");
    }

    public void NewFlightBTN(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/NewFlight.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        split.getItems().set(1 , loader.getRoot());
    }

    public void NewFlightBTNEnter(){
        NewFlightBTN.setStyle("-fx-background-color: #3679c6");
    }

    public void NewFlightBTNExit(){
        NewFlightBTN.setStyle("-fx-background-color: transparent ");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        split.setDividerPositions(0.2);
        split.lookupAll(".split-pane-divider").stream()
                .forEach(div -> div.setMouseTransparent(true));
    }
}
