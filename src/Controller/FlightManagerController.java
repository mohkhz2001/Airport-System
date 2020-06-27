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

    }

    public void flightsBTNExit() {

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        split.setDividerPositions(0.2);
        split.lookupAll(".split-pane-divider").stream()
                .forEach(div ->  div.setMouseTransparent(true) );
    }
}
