package Controller;

import Model.PassengerRepository;
import Model.UserRepository;
import Model.employee;
import Model.passenger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jdk.nashorn.internal.ir.GetSplitState;
import org.controlsfx.control.Notifications;

import javax.management.Notification;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class NewEmployeeController implements Initializable {

    boolean emailValue = false;

    @FXML
    TextField FNameField;
    @FXML
    TextField LNameField;
    @FXML
    TextField phoneNumberField;
    @FXML
    TextField emailField;
    @FXML
    Label usernameLBL;
    @FXML
    Label IDLBL;
    @FXML
    Label idLBL;
    @FXML
    Label userLBL;
    @FXML
    Button addBTN;
    @FXML
    TextField salaryField;

    // new employee
    public void addBTN() {
        // at first check to all field be full
        boolean check1 = false, check2 = false, check3 = false, check4 = false, check5 = false;
        if (FNameField.getText().isEmpty()) {
            FNameField.getStylesheets().add("/resource/canBeEmpty.css");
            check1 = true;
        }
        if (LNameField.getText().isEmpty()) {
            LNameField.getStylesheets().add("/resource/canBeEmpty.css");
            check2 = true;
        }
        if (phoneNumberField.getText().isEmpty()) {
            phoneNumberField.getStylesheets().add("/resource/canBeEmpty.css");
            check3 = true;
        }
        if (emailField.getText().isEmpty() || !emailValue) {
            emailField.getStylesheets().add("/resource/canBeEmpty.css");
            check4 = true;
        }
        if (salaryField.getText().isEmpty()) {
            salaryField.getStylesheets().add("/resource/canBeEmpty.css");
            check5 = true;
        }

        if (!check1 && !check2 && !check3 && !check4 && !check5) {
            UserRepository userRepository = new UserRepository();
            List<employee> employees = userRepository.employer();

            Random rnd = new Random();
            int number = rnd.nextInt(999999);

            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).getID().equals(Integer.toString(number))) {
                    number = rnd.nextInt(999999);
                    i = 0;
                }
            }
            idLBL.setText("ID => ");
            IDLBL.setText(Integer.toString(number));
            userAndPass();
            boolean update = userRepository.newEmployee(FNameField.getText(), LNameField.getText(), IDLBL.getText(), usernameLBL.getText(), usernameLBL.getText(), emailField.getText()
                    , phoneNumberField.getText(), "Employee", salaryField.getText());
            if (update) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.CLOSE);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "cont add", ButtonType.CLOSE);
                alert.showAndWait();
            }

        }
    }

    // start typing in first name field
    public void FNameFieldType() {
        FNameField.setStyle("-fx-border-width: 2 2 2 2 ; -fx-border-color: green ; -fx-background-color:  #a5d72f;-fx-border-radius: 30 ; -fx-background-radius: 30");
    }

    // start typing phone number field
    public void phoneNumberFieldType() {
        phoneNumberField.setStyle("-fx-border-width: 2 2 2 2 ; -fx-border-color: green; -fx-background-color:  #a5d72f;-fx-border-radius: 30 ; -fx-background-radius: 30");
    }

    // start typing last name field
    public void LNameFieldType() {
        LNameField.setStyle("-fx-border-width: 2 2 2 2 ; -fx-border-color: green; -fx-background-color:  #a5d72f;-fx-border-radius: 30 ; -fx-background-radius: 30");
    }

    // start typing email field
    public void emailFieldType() {
        emailField.setStyle("-fx-border-width: 2 2 2 2 ; -fx-border-color: green; -fx-background-color:  #a5d72f;-fx-border-radius: 30 ; -fx-background-radius: 30");
    }

    // start typing salary field
    public void salaryFieldType() {
        salaryField.setStyle("-fx-border-width: 2 2 2 2 ; -fx-border-color: green; -fx-background-color:  #a5d72f;-fx-border-radius: 30 ; -fx-background-radius: 30");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emailField.setOnKeyReleased(event -> {


            UserRepository userRepository = new UserRepository();
            List<employee> employees = userRepository.employer();
            final String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";

            if (emailField.getText().matches(regex)) {

                for (int i = 0; i < employees.size(); i++) {

                    if (employees.get(i).getEmail().equals(emailField.getText())) {

                        emailField.setStyle("-fx-border-width: 2 2 2 2  ; -fx-border-color: red ;-fx-border-radius: 30 ; -fx-background-radius: 30;-fx-background-color:  #a5d72f");

                        emailValue = false;
                    } else {

                        emailField.setStyle("-fx-border-width: 2 2 2 2  ; -fx-border-color: green ;-fx-border-radius: 30 ; -fx-background-radius: 30;-fx-background-color:  #a5d72f");
                        emailValue = true;
                    }
                }
            } else {
                emailField.setStyle("-fx-border-width: 2 2 2 2  ; -fx-border-color: red ;-fx-border-radius: 30 ; -fx-background-radius: 30;-fx-background-color:  #a5d72f");
                emailValue = false;
            }
        });
    }

    // generat automaticly the username and pass
    private void userAndPass() {
        char[] F = FNameField.getText().toCharArray();
        char[] L = LNameField.getText().toCharArray();
        String username = "";
        for (int i = 0; i < F.length; i++) {
            if (i == 0)
                username += F[0];
            if (i == 1)
                username += F[1];
            if (i == F.length - 1) {
                username += F[i];
                break;
            }
        }

        for (int i = 0; i < L.length; i++) {
            if (i == 0)
                username += L[0];
            if (i == 1)
                username += L[1];
            if (i == L.length - 1) {
                username += L[i];
                break;
            }
        }
        userLBL.setText("username => ");
        usernameLBL.setText(username);

    }


}
