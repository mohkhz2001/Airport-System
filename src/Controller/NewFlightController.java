package Controller;

import Model.Airplane;
import Model.AirplaneRepository;
import Model.Flight;
import Model.FlightRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class NewFlightController implements Initializable {
    AirplaneRepository airplaneRepository = new AirplaneRepository();
    FlightRepository flightRepository = new FlightRepository();
    private int capacity;

    @FXML
    TextField FNField;
    @FXML
    TextField registerField;
    @FXML
    TextField depField;
    @FXML
    TextField desField;
    @FXML
    DatePicker datePicker;
    @FXML
    TextField hoursField;
    @FXML
    TextField flightTimeField;
    @FXML
    TextField priceField;
    @FXML
    Label capacityLBL;
    @FXML
    Button addBTN;

    public void addBTN() {
        boolean check = flightRepository.newFlight(FNField.getText().toUpperCase(), registerField.getText().toUpperCase(), depField.getText().toUpperCase(), desField.getText().toUpperCase(), datePicker.getValue().toString(),
                hoursField.getText(), flightTimeField.getText(), "open", capacity, capacity, Integer.parseInt(priceField.getText()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFieldAction();
    }

    private void TextFieldAction() {

        registerField.setOnKeyReleased(event -> {

            if (registerField.getLength() == 6) {
                List<Airplane> airplanes = airplaneRepository.airplaneList();
                for (int i = 0; i < airplanes.size(); i++) {
                    if (airplanes.get(i).getRegister().toUpperCase().equals(registerField.getText().toUpperCase())) {
                        capacityLBL.setText("capacity: " + airplanes.get(i).getSeats());
                        capacity = airplanes.get(i).getSeats();
                        capacityLBL.setTextFill(Color.BLUE);
                        registerField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: green");
                        break;
                    } else if (i == (airplanes.size() - 1)) {
                        capacityLBL.setText("can't find this aircraft..");
                        capacityLBL.setTextFill(Color.RED);
                    }
                }

            }
        });

        registerField.setOnKeyPressed(event -> {
            registerField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: yellow");
        });

        FNField.setOnKeyReleased(event -> {

            List<Flight> flights = flightRepository.flightList();

            for (int i = 0; i < flights.size(); i++) {
                if (flights.get(i).getFlightNumber().equals(FNField.getText().toUpperCase())) {
                    FNField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: red");
                    break;
                } else {
                    FNField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: green");
                }
            }


        });

        depField.setOnKeyReleased(event -> {
            depField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: green");
        });

        desField.setOnKeyReleased(event -> {
            desField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: green");
        });


    }

}
