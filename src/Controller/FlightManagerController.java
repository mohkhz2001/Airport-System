package Controller;

import Model.UserRepository;
import Model.employee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FlightManagerController implements Initializable {

    UserRepository userRepository = new UserRepository();

    private String ID;
    Button newAirplane;

    @FXML
    Button flightsBTN;
    @FXML
    SplitPane split;
    @FXML
    Button airplaneBTN;
    @FXML
    Button NewFlightBTN;
    @FXML
    VBox vbox;

    public void flightsBTN() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FlightsListBTN.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        split.getItems().set(1, loader.getRoot());
    }

    public void airplaneBTN() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/airplaneList.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        airplaneListController airplaneListController = loader.getController();
        airplaneListController.setJob(getJob());
        airplaneListController.loginAs();

        split.getItems().set(1, loader.getRoot());

    }

    public void NewFlightBTN() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/NewFlight.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        split.getItems().set(1, loader.getRoot());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        split.lookupAll(".split-pane-divider").stream()
                .forEach(div -> div.setMouseTransparent(true));
    }

    public void management() {
        if (getJob().equals("Management") || getJob().equals("superAdmin")) {
            newAirplane = new Button("new airplane");
            newAirplane.getStylesheets().add("/resource/flightMenuBTN.css");
            vbox.getChildren().add(newAirplane);
            newAirplaneBTNAction(newAirplane);
        }

    }

    private void newAirplaneBTNAction(Button btn) {

        btn.setOnMouseClicked(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/NewAirplane.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                System.out.println("" + e);
            }
            NewAirplaneController newAirplaneController = loader.getController();
            newAirplaneController.setID(getID());

            split.getItems().set(1, loader.getRoot());
        });

    }

    public String getJob() {
        List<employee> employees = userRepository.employer();
        String job = null;
        for (int i = 0; i < employees.size(); i++) {

            if (employees.get(i).getID().equals(ID)) {
                job = String.valueOf(employees.get(i).getJob());
                break;
            }

        }

        return job;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
