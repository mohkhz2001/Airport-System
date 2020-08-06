package Controller;

import Model.Flight;
import Model.FlightRepository;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;

public class EditFlightController implements Initializable {

    Flight flight;

    @FXML
    ImageView image;
    @FXML
    TextField registerField;
    @FXML
    TextField FNField;
    @FXML
    TextField depField;
    @FXML
    TextField desField;
    @FXML
    TextField dateField;
    @FXML
    TextField hoursField;
    @FXML
    TextField priceField;
    @FXML
    TextField flightTimeField;
    @FXML
    TextField totalCapaField;
    @FXML
    TextField availabelCapaField;
    @FXML
    ChoiceBox<Flight.status> statusBox;
    @FXML
    Label registerLBL;
    @FXML
    Label FNLBL;
    @FXML
    Label depLBL;
    @FXML
    Label desLBL;
    @FXML
    Label dateLBL;
    @FXML
    Label hoursLBL;
    @FXML
    Label priceLBL;
    @FXML
    Label flightTimeLBL;
    @FXML
    Label statusLBL;
    @FXML
    Button updateBTN;

    // if anything in the dep field be changed ==> will set the lbl ("departure" + " ** changed **")
    public void depField() {
        if (!flight.getDep().equals(depField.getText())) {
            depLBL.setTextFill(Color.GREEN);
            depLBL.setText("departure" + " ** changed **");
        } else {
            depLBL.setTextFill(Color.BLACK);
            depLBL.setText("departure");
        }
    }

    // action for the update btn
    public void updateBTN() {

        FlightRepository flightRepository = new FlightRepository();


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "change " + flight.getFlightNumber() + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {

            boolean done = flightRepository.infoUpdate(FNField.getText().toUpperCase(), depField.getText().toUpperCase(), desField.getText().toUpperCase(), dateField.getText().toUpperCase(), hoursField.getText(), flightTimeField.getText(), statusBox.getValue());

            if (done) {

                Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "change " + flight.getFlightNumber(), ButtonType.CLOSE);
                alert1.showAndWait();
                ((Stage) updateBTN.getScene().getWindow()).close();
            } else {
                Alert alert1 = new Alert(Alert.AlertType.ERROR, "cant update " + flight.getFlightNumber(), ButtonType.CLOSE);
                alert1.showAndWait();
                ((Stage) updateBTN.getScene().getWindow()).close();
            }
        }


    }

    // when the mouse enter the update btn
    public void updateBTNEnter() {

    }

    // when the mouse exit the update btn
    public void updateBTNExit() {

    }

    // if anything in the field be changed ==> will set the lbl ( " ** changed **")
    public void desField() {
        if (!flight.getDes().equals(desField.getText())) {
            desLBL.setTextFill(Color.GREEN);
            desLBL.setText("destination" + " ** changed **");
        } else {
            desLBL.setTextFill(Color.BLACK);
            desLBL.setText("destination");
        }
    }

    // if anything in the field be changed ==> will set the lbl ( " ** changed **")
    public void dateField() {
        if (!flight.getDate().equals(dateField.getText())) {
            dateLBL.setTextFill(Color.GREEN);
            dateLBL.setText("date" + " ** changed **");
        } else {
            dateLBL.setTextFill(Color.BLACK);
            dateLBL.setText("date");
        }
    }

    // if anything in the field be changed ==> will set the lbl ( " ** changed **")
    public void hoursField() {
        if (!flight.getHours().equals(hoursField.getText())) {
            hoursLBL.setTextFill(Color.GREEN);
            hoursLBL.setText("hour" + " ** changed **");
        } else {
            hoursLBL.setTextFill(Color.BLACK);
            hoursLBL.setText("hour");
        }
    }

    // if anything in the field be changed ==> will set the lbl ( " ** changed **")
    public void priceLBL() {
        if (flight.getPrice() != Integer.parseInt(priceField.getText())) {
            priceLBL.setTextFill(Color.GREEN);
            priceLBL.setText("price" + " ** changed **");
        } else {
            priceLBL.setTextFill(Color.BLACK);
            priceLBL.setText("price");
        }

    }

    // if anything in the field be changed ==> will set the lbl ( " ** changed **")
    public void flightTimeField() {
        if (!flight.getFlightTime().equals(flightTimeField.getText())) {
            flightTimeLBL.setTextFill(Color.GREEN);
            flightTimeLBL.setText("flight time" + " ** changed **");
        } else {
            flightTimeLBL.setTextFill(Color.BLACK);
            flightTimeLBL.setText("flight time");
        }
    }

    // if anything in the field be changed ==> will set the lbl ( " ** changed **")
    public void statusBox() {
        if (!flight.getStatus().equals(statusBox.getItems())) {
            statusLBL.setTextFill(Color.GREEN);
            statusLBL.setText("status" + " ** changed **");
        } else {
            statusLBL.setTextFill(Color.BLACK);
            statusLBL.setText("status");
        }
    }

    // close the btn action
    public void closeBTN() {
        ((Stage) registerField.getScene().getWindow()).close();
    }

    @Override

    public void initialize(URL location, ResourceBundle resources) {
        updateBTN.setGraphic(new ImageView("file:Icons/update.png"));
        image.setImage(new Image("file:Icons/airplane-edit.png"));
        statusBox.getItems().addAll(Flight.status.open, Flight.status.done, Flight.status.takeoff);
//        desField.getOnInputMethodTextChanged().handle(new InputMethodEvent());
    }

    public void showFlight(Flight info) {
        flight = info;
        registerField.setText(info.getAirplaneRegister());
        FNField.setText(info.getFlightNumber());
        depField.setText(info.getDep());
        desField.setText(info.getDes());
        dateField.setText(info.getDate());
        hoursField.setText(info.getHours());
        priceField.setText(Integer.toString(info.getPrice()));
//        registerField.setText(info.getAirplaneRegister());
        flightTimeField.setText(info.getFlightTime());
        statusBox.setValue(info.getStatus());
        totalCapaField.setText(Integer.toString(info.getTotalCapacity()));
        availabelCapaField.setText(Integer.toString(info.getCapacity()));

    }

}
