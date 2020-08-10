package Controller;

import Model.PassengerRepository;
import Model.passenger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class SignUpController implements Initializable {
    PassengerRepository passengerRepository = new PassengerRepository();

    boolean usernameValue = false, emailValue = false;
    @FXML
    TextField firstNameField;
    @FXML
    TextField lastNameField;
    @FXML
    TextField usernameField;
    @FXML
    TextField emailField;
    @FXML
    PasswordField passwordField;
    @FXML
    PasswordField rePasswordField;
    @FXML
    ImageView usernameImage;
    @FXML
    ImageView emailImage;
    @FXML
    Button signUpBTN;
    @FXML
    CheckBox rulesCheck;
    @FXML
    Label emailErrorLBL;
    @FXML
    Label usernameErrorLBL;

    // click on the sign up
    public void signUpBTN() {
        // check which on is empty
        // if non
        if (!firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty() && !passwordField.getText().isEmpty() && !rePasswordField.getText().isEmpty() && !emailField.getText().isEmpty() && rulesCheck.isSelected()) {

            boolean add = passengerRepository.PassengerAdder(firstNameField.getText(), lastNameField.getText(), usernameField.getText(), emailField.getText(), passwordField.getText(), "", Long.toString(randomID()));
            if (add) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, ".....", ButtonType.CLOSE);
                alert.showAndWait();
                ((Stage) signUpBTN.getScene().getWindow()).close();
                loginLoader();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR , "cant add" , ButtonType.CLOSE);
                alert.showAndWait();
                loginLoader();
            }
        }
        else {
            if (firstNameField.getText().isEmpty()) {
                firstNameField.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 3 0 ; -fx-background-color: transparent");
            } else {
                firstNameField.setStyle("-fx-border-color: green; -fx-border-width: 0 0 3 0 ; -fx-background-color: transparent");
            }

            if (lastNameField.getText().isEmpty()) {
                lastNameField.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 3 0 ; -fx-background-color: transparent");
            } else {
                lastNameField.setStyle("-fx-border-color: green; -fx-border-width: 0 0 3 0 ; -fx-background-color: transparent");
            }

            if (passwordField.getText().isEmpty()) {
                passwordField.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 3 0 ; -fx-background-color: transparent ");
                rePasswordField.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 3 0 ; -fx-background-color: transparent");
            } else {
                if (!passwordField.getText().equals(rePasswordField.getText())) {
                    rePasswordField.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 3 0 ; -fx-background-color: transparent");
                    passwordField.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 3 0 ; -fx-background-color: transparent");
                } else {
                    passwordField.setStyle("-fx-border-color: green; -fx-border-width: 0 0 3 0 ; -fx-background-color: transparent");
                    rePasswordField.setStyle("-fx-border-color: green; -fx-border-width: 0 0 3 0 ; -fx-background-color: transparent");
                }

            }

            if (!emailValue) {
                emailField.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 3 0 ; -fx-background-color: transparent");
            } else {
                emailField.setStyle("-fx-border-color: green; -fx-border-width: 0 0 3 0 ; -fx-background-color: transparent");
            }

            if (!usernameValue) {
                usernameField.setStyle("-fx-border-color: red ; -fx-border-width: 0 0 3 0 ; -fx-background-color: transparent");
            } else {
                usernameField.setStyle("-fx-border-color: green; -fx-border-width: 0 0 3 0 ; -fx-background-color: transparent");
            }

            if (!rulesCheck.isSelected()) {
                rulesCheck.setStyle("-fx-text-fill: red");
            }
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        usernameField.setOnKeyReleased(event -> {


            PassengerRepository passengerRepository = new PassengerRepository();
            List<passenger> passengerList = passengerRepository.passengerList();

            for (int i = 0; i < passengerList.size(); i++) {

                if (passengerList.get(i).getUsername().equals(usernameField.getText())) {

                    usernameImage.setImage(new Image("file:Icons/notCorrect.png"));
                    usernameField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: red ; -fx-background-color: transparent");
                    usernameErrorLBL.setText("this username is similar to another user");
                    usernameErrorLBL.setTextFill(Color.RED);
                    usernameValue = false;

                } else {

                    usernameImage.setImage(new Image("file:Icons/correct.png"));
                    usernameField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: green ; -fx-background-color: transparent");
                    usernameErrorLBL.setText(null);
                    usernameValue = true;
                }
            }

        });


        emailField.setOnKeyReleased(event -> {


            PassengerRepository passengerRepository = new PassengerRepository();
            List<passenger> passengerList = passengerRepository.passengerList();
            final String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";

            if (emailField.getText().matches(regex)) {

                for (int i = 0; i < passengerList.size(); i++) {

                    if (passengerList.get(i).getEmail().equals(emailField.getText())) {

                        emailImage.setImage(new Image("file:Icons/notCorrect.png"));
                        emailField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: red ; -fx-background-color: transparent");
                        emailErrorLBL.setText("this Email signed up before");
                        emailErrorLBL.setTextFill(Color.RED);
                        emailValue = false;

                    } else {

                        emailImage.setImage(new Image("file:Icons/correct.png"));
                        emailField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: green ; -fx-background-color: transparent");
                        emailErrorLBL.setText(null);
                        emailValue = true;
                    }
                }
            } else {
                emailImage.setImage(new Image("file:Icons/notCorrect.png"));
                emailField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: red ; -fx-background-color: transparent");
                emailErrorLBL.setText("incorrect input");
                emailErrorLBL.setTextFill(Color.RED);
                emailValue = false;
            }
        });

    }
    // generat the random number do id
    private long randomID() {
        Random random = new Random();
        int id = random.nextInt(999999999);
        List<passenger> passengers = passengerRepository.passengerList();
        for (int i = 0; i < passengers.size(); i++) {
            if (passengers.get(i).getID().equals(id)) {
                id = random.nextInt(9999999);
                i = 0;
            }
        }
        return id;
    }

    private void loginLoader() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Login.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.getRoot()));
        stage.setTitle("sign in");
        stage.getIcons().add(new Image("file:Icons/login.png"));
//        primaryStage.setResizable(false);
        stage.show();
    }

}
