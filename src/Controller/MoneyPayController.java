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
    private int counter = 0;
    // make the empty field error final ....
    public static final String errorField = "can't be empty...";

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
    Label amountLBLError;
    @FXML
    ImageView imageView;
    @FXML
    Label cardNumberLBL;
    @FXML
    Label amountLBL;

    // set the release action on the amount field
    public void amountFieldReleased() {
        //1. just number can input
        amountField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                amountField.setText(newValue.replaceAll("[^\\d]", ""));

            } else {
                amountField.setStyle("-fx-background-radius: 30 ;");

            }
        });
        // put ',' in the amount lbl
        if (amountField.getLength() > 3 && amountField.getText().matches("[0-9]+"))
            amountLBL.setText(setAmountLBL(Long.parseLong(amountField.getText())));
        else
            amountLBL.setText(amountField.getText());
    }

    // when start typing the background will be change to the withe if when  were empty and clicked on the pay btn
    public void amountFieldTyped() {
        amountField.setStyle("-fx-background-radius: 30 ; -fx-background-color: white");
    }

    // when start typing the background will be change to the withe if when  were empty and clicked on the pay btn
    public void dateFieldTyped() {
        dateField.setStyle("-fx-background-radius: 30 ; -fx-background-color: white");
    }

    // when start typing the background will be change to the withe if when  were empty and clicked on the pay btn
    public void cardNumberFieldTyped() {
        cardNumberField.setStyle("-fx-background-radius: 30 ; -fx-background-color: white");

        counter++;
        if (counter == 5) {
            cardNumberField.appendText(" - ");/// important
            counter = 1;
        }
    }

    // when start typing the background will be change to the withe if when  were empty and clicked on the pay btn
    public void cvv2FieldTyped() {
        cvv2Field.setStyle("-fx-background-radius: 30 ; -fx-background-color: white");
    }

    // when start typing the background will be change to the withe if when  were empty and clicked on the pay btn
    public void passFieldTyped() {
        passField.setStyle("-fx-background-radius: 30 ; -fx-background-color: white");
    }

    // click action for pat btn...
    public void payBTN() {
        // at first check the length of the field and selection of the checkbox
        if (rulesCheckBox.isSelected() && !amountField.getText().isEmpty() && !dateField.getText().isEmpty() && !cardNumberField.getText().isEmpty() &&
                !cvv2Field.getText().isEmpty() && !passField.getText().isEmpty()) {
            // if are all ok
            PassengerRepository passengerRepository = new PassengerRepository();
            List<passenger> passengerList = passengerRepository.passengerList();
            //search the person by ID
            for (int i = 0; i < passengerList.size(); i++) {

                if (passengerList.get(i).getID().equals(getID())) {
                    // when found the person
                    // get the extant and plus the amount of want to add.
                    int a = Integer.parseInt(passengerList.get(i).getMoney()) + Integer.parseInt(amountField.getText());
                    // get this to the function in the passenger repo to update the DB
                    boolean update = passengerRepository.increaseMoney(Integer.toString(a), passengerList.get(i).getID());
                    if (update) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "done", ButtonType.CLOSE);
                        alert.showAndWait();
                        fieldNull();
                    }
                    // get out of the for..
                    break;

                }

            }

        }
        // if one of them are empty the process will be cancel and background will be red and write the ==> "errorField"
        else {
            /*
            check witch on is empty .
             */
            if (!rulesCheckBox.isSelected()) {
                rulesCheckBox.setTextFill(Color.RED);
            }
            if (amountField.getText().isEmpty()) {
                amountField.setPromptText(errorField);
                amountField.setStyle("-fx-background-color: #d44545 ; -fx-background-radius: 30");
            }
            if (dateField.getText().isEmpty()) {
                dateField.setPromptText(errorField);
                dateField.setStyle("-fx-background-color: #d44545 ; -fx-background-radius: 30");
            }
            if (passField.getText().isEmpty()) {
                passField.setPromptText(errorField);
                passField.setStyle("-fx-background-color: #d44545 ; -fx-background-radius: 30");
            }
            if (cvv2Field.getText().isEmpty()) {
                cvv2Field.setPromptText(errorField);
                cvv2Field.setStyle("-fx-background-color: #d44545 ; -fx-background-radius: 30");
            }
            if (cardNumberField.getText().isEmpty()) {
                cardNumberField.setPromptText(errorField);
                cardNumberField.setStyle("-fx-background-color: #d44545 ; -fx-background-radius: 30");
            }

        }


    }

    // add the Image to the pay btn when the mousse enter them
    public void payBTNEnter() {
        payBTN.setGraphic(new ImageView("file:Icons/pay.png"));
        payBTN.setText(null);
    }

    // after exit the btn will show the pas
    public void payBTNExit() {
        payBTN.setText("PAY");
        payBTN.setGraphic(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set the image view
        imageView.setImage(new Image("file:Icons/money.png"));
    }

    private String setAmountLBL(Long amount) {
        char[] a = Long.toString(amount).toCharArray();
        int counter = 0;

        int b = a.length;
        int c = b / 3 + b;
        String[] d = new String[c];
        int j = c;
        for (int i = (a.length - 1); i <= a.length; i--) {
            j--;
            if (counter == 3) {
                d[j] = ",";
                i++;
                counter = -1;
            } else {
                d[j] = String.valueOf(a[i]);

            }
            if (i == 0)
                break;
            counter++;

        }

        String Final = "";

        for (int i = 0; i < c; i++) {
            Final += d[i];
        }

        return Final;

    }

    // make the field null
    private void fieldNull() {
        amountField.setText("0");
        dateField.setText(null);
        cardNumberField.setText(null);
        cvv2Field.setText(null);
        passField.setText(null);
        emailField.setText(null);
        rulesCheckBox.setSelected(false);
    }

    // get the ID
    public String getID() {
        return ID;
    }

    // set the ID
    public void setID(String ID) {
        this.ID = ID;
    }

}
