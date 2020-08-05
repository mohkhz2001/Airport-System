package Controller;

import Model.Support;
import Model.SupportRepository;
import Model.UserRepository;
import Model.employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeProfileController implements Initializable {
    UserRepository userRepository = new UserRepository();

    private String ID;
    private boolean editClicked = false;

    @FXML
    TextField nameField;
    @FXML
    TextField idField;
    @FXML
    TextField phoneNumberField;
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    PasswordField rePassField;
    @FXML
    TextField addressField;
    @FXML
    TextField emailField;
    @FXML
    TextField salaryField;
    @FXML
    Button submitBTN;
    @FXML
    Circle circel;
    @FXML
    Button editBTN;
    @FXML
    VBox rePass;

    // submit btn action ==> set the info to the DB
    public void submitBTN() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "do you want to submit??", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            boolean update = userRepository.infoUpdate(getID(), addressField.getText(), emailField.getText(), phoneNumberField.getText(), usernameField.getText(), passwordField.getText());
            if (update) {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "done", ButtonType.CLOSE);
                alert1.showAndWait();
                ((Stage) submitBTN.getScene().getWindow()).close();
            } else {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "cant update...", ButtonType.CLOSE);
                alert1.showAndWait();
            }
        } else {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "canceled", ButtonType.CLOSE);
            alert1.showAndWait();
        }

    }

    // when the user open this page just can see the info but if click this brn can edit
    public void editBTN() {
        editClicked = true;
        phoneNumberField.setEditable(true);
        if (!phoneNumberField.getText().isEmpty()) {
            phoneNumberField.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 0 3 ; -fx-border-color: green");
        } else {
            phoneNumberField.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 0 3 ; -fx-border-color: yellow");

        }

        usernameField.setEditable(true);
        usernameField.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 0 3 ; -fx-border-color: green");

        passwordField.setEditable(true);
        passwordField.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 0 3 ; -fx-border-color: green");


        addressField.setEditable(true);
        if (!addressField.getText().isEmpty()) {
            addressField.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 0 3 ; -fx-border-color: green");
        } else {
            addressField.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 0 3 ; -fx-border-color: yellow");

        }

        emailField.setEditable(true);
        if (!emailField.getText().isEmpty()) {
            emailField.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 0 3 ; -fx-border-color: green");
        } else {
            emailField.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 0 3 ; -fx-border-color: yellow");

        }

        submitBTN.setDisable(false);
    }

    //if user after click on the edit btn want to edit password , re-pass field will be active
    public void passwordField() {
        if (editClicked) {
            rePass.setDisable(false);
            rePassField.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 0 3 ; -fx-border-color: green");

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set the graph for the circel and edit btn
        circel.setFill(new ImagePattern(new Image("file:Icons/aaa.png")));
        editBTN.setGraphic(new ImageView("file:Icons/edit.png"));
    }

    // after click on the profile btn app should fill the all the field
    public void fillField() {
        List<employee> employees = userRepository.employer();
        // search the user
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getID().equals(getID())) {
                nameField.setText(employees.get(i).getFirstName() + " " + employees.get(i).getLastName());
                idField.setText(getID());
                phoneNumberField.setText(employees.get(i).getPhoneNumber());
                usernameField.setText(employees.get(i).getUsername());
                passwordField.setText(employees.get(i).getPassword());
                addressField.setText(employees.get(i).getAddress());
                emailField.setText(employees.get(i).getEmail());
                salaryField.setText(setSalary(employees.get(i).getSalary()));
                break;
            }
        }
    }

    // this function set the salary field like this => 1,120,000
    private String setSalary(String salary) {
        char[] a = salary.toCharArray();
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
