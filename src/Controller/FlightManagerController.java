package Controller;

import Model.Person;
import Model.UserRepository;
import Model.employee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
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

    // flightBTN action handler ==> show the all flight
    public void flightsBTN() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FlightsListBTN.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        split.getItems().set(1, loader.getRoot());
    }

    // airplaneBTN action handler ==> show the airplane and if click each airplane show the flights
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

    // newFlightBTN ==> make the new flight.
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
        // set the icon for the btn
        flightsBTN.setGraphic(new ImageView("file:Icons/flights2.png"));
        airplaneBTN.setGraphic(new ImageView("file:Icons/airplane2.png"));
//        newAirplane.setGraphic(new ImageView("file:Icons/"));
        split.lookupAll(".split-pane-divider").stream()
                .forEach(div -> div.setMouseTransparent(true));
    }

    //if the login was management or super admin add the new airplane btn => can add new airplane for flights
    public void management() {
        if (getJob().equals(Person.Job.Management) || getJob().equals(Person.Job.superAdmin)) {
            newAirplane = new Button("new Airplane");
            newAirplane.getStylesheets().add("/resource/flightMenuBTN.css");
            vbox.getChildren().add(newAirplane);
            newAirplaneBTNAction(newAirplane);
        }

    }

    // action on the airplane BTN
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

    // get the job ==> return the job as String
    public Person.Job getJob() {
        List<employee> employees = userRepository.employer();
        Person.Job job = null;
        for (int i = 0; i < employees.size(); i++) {

            if (employees.get(i).getID().equals(ID)) {
                job = employees.get(i).getJob();
                break;
            }

        }

        return job;
    }

    //get the ID
    public String getID() {
        return ID;
    }

    //set the ID
    public void setID(String ID) {
        this.ID = ID;
    }
}
