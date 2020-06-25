package Controller;

import Model.PassengerRepository;
import Model.passenger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MoneyPayController implements Initializable {

    private String ID;
    int counter = 0, counter1 = 0;

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
    @FXML
    ImageView imageView;
    @FXML
    Label cardNumberLBL;

    public void amountField() {

        amountField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                amountField.setText(newValue.replaceAll("[^\\d]", ""));

            }else {
                amountField.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 3 0 ; -fx-border-color: green");

                if (progressBar.getProgress() < .2)
                    progressBar.setProgress(.2);
            }
        });
    }

    public void dateField() {

        dateField.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 3 0 ; -fx-border-color: green");
        if (progressBar.getProgress() < .6)
            progressBar.setProgress(.6);

    }

    public void cardNumberField() {

        cardNumberField.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 3 0 ; -fx-border-color: green");
        counter++;
        if (counter == 5) {
            cardNumberField.appendText(" - ");/// important
            counter = 1;
        }
        if (progressBar.getProgress() < .4)
            progressBar.setProgress(.4);
    }

    public void cvv2Field() {
        cvv2Field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                passField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        cvv2Field.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 3 0 ; -fx-border-color: green");
        if (progressBar.getProgress() < .8)
            progressBar.setProgress(.8);
    }

    public void passField() {
        passField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                passField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        passField.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 3 0 ; -fx-border-color: green");
        if (progressBar.getProgress() < 1)
            progressBar.setProgress(1);
    }

    public void emailField() {
        emailField.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 3 0 ; -fx-border-color: green");
    }

    public void payBTN() {
        progressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        if (rulesCheckBox.isSelected() && !amountField.getText().isEmpty() && !dateField.getText().isEmpty() && !cardNumberField.getText().isEmpty() &&
                !cvv2Field.getText().isEmpty() && !passField.getText().isEmpty()) {

            PassengerRepository passengerRepository = new PassengerRepository();
            List<passenger> passengerList = passengerRepository.passengerList();

            for (int i = 0; i < passengerList.size(); i++) {

                if (passengerList.get(i).getID().equals(getID())) {

                    int a = Integer.parseInt(passengerList.get(i).getMoney()) + Integer.parseInt(amountField.getText());

                    passengerRepository.increaseMoney(Integer.toString(a), passengerList.get(i).getID());

                    progressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
                    break;

                }

            }

        } else {
            if (!rulesCheckBox.isSelected()) {
                rulesCheckBox.setTextFill(Color.RED);
            }
            if (amountField.getText().isEmpty()) {
                amountField.setStyle("-fx-background-color: transparent ; -fx-border-color: red ; -fx-border-width:  0 0 3 0");
            }
            if (dateField.getText().isEmpty()) {
                dateField.setStyle("-fx-background-color: transparent ; -fx-border-color: red ; -fx-border-width:  0 0 3 0");
            }
            if (passField.getText().isEmpty()) {
                passField.setStyle("-fx-background-color: transparent ; -fx-border-color: red ; -fx-border-width:  0 0 3 0");
            }
            if (cvv2Field.getText().isEmpty()) {
                cvv2Field.setStyle("-fx-background-color: transparent ; -fx-border-color: red ; -fx-border-width:  0 0 3 0");
            }
            if (cardNumberField.getText().isEmpty()) {
                cardNumberField.setStyle("-fx-background-color: transparent ; -fx-border-color: red ; -fx-border-width:  0 0 3 0");
            }

        }


    }

    public void payBTNEnter() {
        payBTN.setGraphic(new ImageView("file:Icons/pay.png"));
        payBTN.setText(null);
    }

    public void payBTNExit() {
        payBTN.setText("PAY");
        payBTN.setGraphic(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageView.setImage(new Image("file:Icons/money.png"));
        progressBar.setDisable(true);
        progressBar.setProgress(0);


    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

}
