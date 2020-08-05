package Controller;

import Model.Airplane;
import Model.AirplaneRepository;
import Model.Flight;
import Model.FlightRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

    // add the new flight
    public void addBTN() {
        boolean check = flightRepository.newFlight(FNField.getText().toUpperCase(), registerField.getText().toUpperCase(), depField.getText().toUpperCase(), desField.getText().toUpperCase(), datePicker.getValue().toString(),
                hoursField.getText(), flightTimeField.getText(), "open", capacity, capacity, Integer.parseInt(priceField.getText()));
        if (check) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "done", ButtonType.CLOSE);
            alert.showAndWait();
            setNull();
            setBorder();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "can't add ", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFieldAction();
    }

    // set the text field action ==> register , FN , dep , des
    private void TextFieldAction() {
        // just can input 6 char
        registerField.setOnKeyReleased(event -> {

            if (registerField.getLength() == 6) {
                List<Airplane> airplanes = airplaneRepository.airplaneList();
                for (int i = 0; i < airplanes.size(); i++) {
                    if (airplanes.get(i).getRegister().toUpperCase().equals(registerField.getText().toUpperCase())) {
                        capacityLBL.setText("capacity: " + airplanes.get(i).getSeats());
                        capacity = airplanes.get(i).getSeats();
                        capacityLBL.setTextFill(Color.BLUE);
                        registerField.setStyle("-fx-border-width: 2 2 2 2 ; -fx-border-color: green ; -fx-border-radius: 30 ; -fx-background-radius: 30");
                        break;
                    } else if (i == (airplanes.size() - 1)) {
                        capacityLBL.setText("can't find this aircraft..");
                        capacityLBL.setTextFill(Color.RED);
                    }
                }

            }
        });
        // when start typing set the register field border
        registerField.setOnKeyPressed(event -> {
            registerField.setStyle("-fx-border-width: 2 2 2 2 ; -fx-border-color: yellow; -fx-border-radius: 30 ; -fx-background-radius: 30");
        });
        // check the flight number ==> is there or not
        FNField.setOnKeyReleased(event -> {

            List<Flight> flights = flightRepository.flightList();

            for (int i = 0; i < flights.size(); i++) {
                if (flights.get(i).getFlightNumber().equals(FNField.getText().toUpperCase())) {
                    FNField.setStyle("-fx-border-width: 2 2 2 2 ; -fx-border-color: red; -fx-border-radius: 30 ; -fx-background-radius: 30");
                    break;
                } else {
                    FNField.setStyle("-fx-border-width: 2 2 2 2 ; -fx-border-color: green; -fx-border-radius: 30 ; -fx-background-radius: 30");
                }
            }


        });
        //when start typing set the dep field border
        depField.setOnKeyReleased(event -> {
            depField.setStyle("-fx-border-width: 2 2 2 2 ; -fx-border-color: green; -fx-border-radius: 30 ; -fx-background-radius: 30");
        });
        //when start typing set the des field border
        desField.setOnKeyReleased(event -> {
            desField.setStyle("-fx-border-width: 2 2 2 2 ; -fx-border-color: green; -fx-border-radius: 30 ; -fx-background-radius: 30");
        });

    }

    //after add the new flight to DB should make the field null(empty)
    private void setNull() {
        FNField.setText(null);
        registerField.setText(null);
        depField.setText(null);
        desField.setText(null);
        hoursField.setText(null);
        flightTimeField.setText(null);
        priceField.setText(null);
        capacityLBL.setText(null);
    }
    // set the border 0 0 0 0 after click on the add
    private void setBorder(){
        FNField.setStyle("-fx-border-radius: 30 ; -fx-border-width: 0 0 0 0;  -fx-border-radius: 30 ; -fx-background-radius: 30");
        registerField.setStyle("-fx-border-radius: 30 ; -fx-border-width: 0 0 0 0; -fx-border-radius: 30 ; -fx-background-radius: 30 ");
        depField.setStyle("-fx-border-radius: 30 ; -fx-border-width: 0 0 0 0;  -fx-border-radius: 30 ; -fx-background-radius: 30");
        hoursField.setStyle("-fx-border-radius: 30 ; -fx-border-width: 0 0 0 0; -fx-border-radius: 30 ; -fx-background-radius: 30 ");
        flightTimeField.setStyle("-fx-border-radius: 30 ; -fx-border-width: 0 0 0 0;  -fx-border-radius: 30 ; -fx-background-radius: 30");
        priceField.setStyle("-fx-border-radius: 30 ; -fx-border-width: 0 0 0 0; -fx-border-radius: 30 ; -fx-background-radius: 30 ");
    }

}
