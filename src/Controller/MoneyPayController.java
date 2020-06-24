package Controller;

import Model.PassengerRepository;
import Model.passenger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MoneyPayController implements Initializable {

    private String ID;
    int counter = 0;

    @FXML
    ProgressBar progressBar;
    @FXML
    TextField amountField;
    @FXML
    TextField dateField;
    @FXML
    TextField cardNumberField;
    @FXML
    PasswordField cvv2Field;
    @FXML
    PasswordField passField;
    @FXML
    CheckBox rulesCheckBox;
    @FXML
    TextField emailField;
    @FXML
    Button payBTN;
    @FXML
    Label amountLBL;

    public void amountField() {

        amountField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                amountField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        progressBar.setProgress(.2);
    }

    public void dateField() {
        dateField.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 3 0 ; -fx-border-color: green");
        progressBar.setProgress(.6);
    }

    public void cardNumberField() {

        cardNumberField.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 3 0 ; -fx-border-color: green");
        counter++;
        if (counter == 5) {
            cardNumberField.appendText(" - ");/// important
            counter = 1;
        }
        progressBar.setProgress(.4);
    }

    public void cvv2Field() {
        cvv2Field.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 3 0 ; -fx-border-color: green");
        progressBar.setProgress(.8);
    }

    public void passField() {
        passField.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 3 0 ; -fx-border-color: green");
        progressBar.setProgress(1);
    }

    public void emailField() {
        emailField.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 3 0 ; -fx-border-color: green");
    }

    public void payBTN(){
//        if ()

        PassengerRepository passengerRepository = new PassengerRepository();
        List<passenger> passengerList = passengerRepository.passengerList();
        for (int i = 0; i < passengerList.size(); i++) {
            if (passengerList.get(i).getID().equals(getID())){

                int a = Integer.parseInt(passengerList.get(i).getMoney()) + Integer.parseInt(amountField.getText());
                passengerList.get(i).setMoney(Integer.toString(a));
                break;

            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progressBar.setDisable(true);
        progressBar.setProgress(0);



    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

//    public boolean check(){
//        String b = amountField.getText();
//    }
}
