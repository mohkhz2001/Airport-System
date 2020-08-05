package Controller;

import Model.Airplane;
import Model.AirplaneRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.xml.soap.Text;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NewAirplaneController implements Initializable {

    private String ID;

    @FXML
    ImageView image;
    @FXML
    TextField registerField;
    @FXML
    TextField typeField;
    @FXML
    TextField seatsField;
    @FXML
    Label registerLBL;
    @FXML
    Label typeLBL;
    @FXML
    Label seatsLBL;
    @FXML
    Label LBL;
    @FXML
    Button addBTN;

    // start typing in register field
    public void registerFieldType() {
        registerField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: green ; -fx-background-color:  transparent");
    }

    // max get the 6 char
    public void registerFieldPresse() {
        addTextLimiter(registerField, 6);
    }

    // max get the 8 char
    public void typeFieldPressed() {
        addTextLimiter(typeField, 8);
    }

    // start typing in type of aircraft
    public void typeFieldType() {
        typeField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: green ; -fx-background-color:  transparent");
    }

    // max get the 4 char
    public void seatsFieldPressed() {
        addTextLimiter(seatsField, 4);
    }

    // start typing in seats field
    public void seatsFieldType() {
        seatsField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: green ; -fx-background-color:  transparent");
    }

    // click on the add btn
    public void addBTN() {

        AirplaneRepository airplaneRepository = new AirplaneRepository();
        List<Airplane> airplanes = airplaneRepository.airplaneList();

        boolean newRegister = false;// check is it new or not
        for (int i = 0; i < airplanes.size(); i++) {

            if (airplanes.get(i).getRegister().equals(registerField.getText().toUpperCase())) {
                newRegister = false;
                registerLBL.setText("this register used before \nand you can't add it again");
                registerLBL.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: red");
            } else if (i == airplanes.size() - 1)
                newRegister = true;
        }
        //if non of the field are empty => will add this to DB
        if (newRegister && !registerField.getText().isEmpty() && !typeField.getText().isEmpty() && !seatsField.getText().isEmpty()) {
            boolean check = airplaneRepository.addNewAirplane(registerField.getText().toUpperCase(), typeField.getText().toUpperCase(), seatsField.getText());

            if (check) {
                LBL.setText("   new airplane added.... ;)) ");
                LBL.setStyle("-fx-background-color: #14e014  ;  -fx-background-radius: 30;\n" +
                        "    -fx-background-insets: 0, 1, 2, 3, 0;");
            } else {
                LBL.setText("   error to airplane added.... ");
                LBL.setStyle("-fx-background-color: red");
            }

        }
        // if on of the field was empty => make that border red
        else {
            if (registerField.getText().isEmpty()) {
                registerField.setStyle("-fx-background-color: transparent ; -fx-border-color: red ; -fx-border-width:  0 0 3 0");
                registerLBL.setText("can't be empty");
                registerField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: red");
            }
            if (typeField.getText().isEmpty()) {
                typeField.setStyle("-fx-background-color: transparent ; -fx-border-color: red ; -fx-border-width:  0 0 3 0");
                typeLBL.setText("can't be empty");
                typeField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: red");
            }
            if (seatsField.getText().isEmpty()) {
                seatsField.setStyle("-fx-background-color: transparent ; -fx-border-color: red ; -fx-border-width:  0 0 3 0");
                seatsLBL.setText("can't be empty");
                seatsField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: red");
            }

        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set the image...
        image.setImage(new Image("file:Icons/newAirplane.jpeg"));
    }

    // et the field length
    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);

                }
            }
        });
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
