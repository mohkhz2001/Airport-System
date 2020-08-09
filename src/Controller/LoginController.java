package Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;


public class LoginController implements Initializable {


    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    Button signInBTN;
    @FXML
    ImageView welcomeImage;
    @FXML
    ImageView googleIcon;
    @FXML
    ImageView facebookIcon;
    @FXML
    ImageView ivaoIcon;
    @FXML
    Label passForgotLBL;
    @FXML
    Label signUpLBL;

    // this part should be in 2 or there functions.....
    public void signInBTN() {

        PassengerRepository passengerRepository = new PassengerRepository();
        List<passenger> passengerList = passengerRepository.passengerList();
//
//        usernameField.setText("khz");
//        passwordField.setText("1234");

        for (int i = 0; i < passengerList.size(); i++) {
            //check the passenger list.....
            if (passengerList.get(i).getUsername().equals(usernameField.getText()) && passengerList.get(i).getPassword().equals(passwordField.getText())) {
//
                passengerPage(passengerList.get(i));
                break;
            }
            //check the employee (employee , manager , super admin)
            else if (i == passengerList.size() - 1) {

                UserRepository userRepository = new UserRepository();
                List<employee> employeeList = userRepository.employer();

                for (int j = 0; j < employeeList.size(); j++) {
                    if (employeeList.get(j).getUsername().equals(usernameField.getText()) && employeeList.get(j).getPassword().equals(passwordField.getText())) {

                        managerPage(employeeList.get(j));
                        break;
                    } else if (j == employeeList.size() - 1) {

                        usernameField.setStyle("-fx-border-width: 0 0 0 3 ; -fx-border-color: red ; -fx-background-color:  #c2c2c2 ; -fx-text-inner-color: black");
                        passwordField.setStyle("-fx-border-width: 0 0 0 3; -fx-border-color: red ; -fx-background-color:  #c2c2c2 ; -fx-text-inner-color: black");
                        passForgotLBL.setTextFill(Color.RED);

                    }
                }

            }
        }

    }

    // after enter the btn => sign in btn will have the graphic
    public void enterSignInBTN() {
        signInBTN.setText("");
        signInBTN.setStyle("-fx-background-color: green; -fx-border-radius: 30 ; -fx-background-radius: 30");
        signInBTN.setGraphic(new ImageView("file:Icons/login btn .png"));
    }

    // make the graphic null
    public void exiteSignInBTN() {
        signInBTN.setStyle("-fx-background-color:  #212121; -fx-border-radius: 30 ; -fx-background-radius: 30");
        signInBTN.setText("sign in");
        signInBTN.setGraphic(null);
    }

    // action for image (google) ==> that open the google
    public void googleIconClicked() {
        Desktop desktop = java.awt.Desktop.getDesktop();
        try {
            //specify the protocol along with the URL
            java.net.URI oURL = new URI(
                    "https://www.google.com");
            desktop.browse(oURL);
        } catch (URISyntaxException | IOException d) {
            // TODO Auto-generated catch block
            d.printStackTrace();
        }
    }

    // action for image (facebook) ==> that open the facebook site
    public void facebookIconClickec() {
        Desktop desktop = java.awt.Desktop.getDesktop();
        try {
            //specify the protocol along with the URL
            java.net.URI oURL = new URI(
                    "https://facebook.com");
            desktop.browse(oURL);
        } catch (URISyntaxException | IOException d) {
            // TODO Auto-generated catch block
            d.printStackTrace();
        }
    }

    // action for image (IVAO) ==> open the ivao site
    public void ivaoIconClickec() {
        Desktop desktop = java.awt.Desktop.getDesktop();
        try {
            //specify the protocol along with the URL
            java.net.URI oURL = new URI(
                    "https://ivao.aero/Login.aspx");
            desktop.browse(oURL);
        } catch (URISyntaxException | IOException d) {
            // TODO Auto-generated catch block
            d.printStackTrace();
        }
    }

    // sign up lbl action
    public void signUpLBLClick() {

        ((Stage) signInBTN.getScene().getWindow()).close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/SignUp.fxml"));//
        try {
            loader.load();
        } catch (IOException e) {
            System.out.println("theres problem to load paasenger view \n" + e);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.getRoot()));
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:Icons/signUp.png"));
        stage.setTitle("new passenger...");
        stage.show();

        System.out.println("not yet");
    }

    // recovery password
    public void passForgotLBLClick() {
        ((Stage) passForgotLBL.getScene().getWindow()).close();

        String email = email();
        String username = username();

        if (passengerRecovery(email, username)) {
            loginLoader();
        } else if (employeeRecovery(email, username)) {
            loginLoader();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "cant find this user", ButtonType.CLOSE);
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set the icons
        welcomeImage.setImage(new Image("file:Icons/welcome.png"));
        googleIcon.setImage(new Image("file:Icons/google.png"));
        facebookIcon.setImage(new Image("file:Icons/facebook.png"));
        ivaoIcon.setImage(new Image("file:Icons/aaaaa.png"));
    }

    // if the login man was employee , manager , super admin
    private void managerPage(employee employee) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/manager.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        managerController managerController = loader.getController();
        managerController.setID(employee.getID());

        if (employee.getJob().equals(Person.Job.Employee)) {// check if employee
            managerController.employee(employee.getID());
        } else if (employee.getJob().equals(Person.Job.Management)) {// check if manager
            managerController.manager(employee.getID());
        } else if (employee.getJob().equals(Person.Job.superAdmin)) {// check if super admin
            managerController.superAdmin(employee.getID());
        }

        ((Stage) signInBTN.getScene().getWindow()).close();
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.getRoot()));
        stage.show();
    }

    // if the login man was passenger
    private void passengerPage(passenger passenger) {
        ((Stage) signInBTN.getScene().getWindow()).close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Passenger.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            System.out.println("theres problem to load paasenger view \n" + e);
        }

        PassengerController passengerController = loader.getController();
        passengerController.setID(passenger.getID());
        passengerController.setUserNameLBL(passenger.getID());

        Stage stage = new Stage();
        stage.setScene(new Scene(loader.getRoot()));
        stage.setResizable(false);
        stage.show();
    }

    // enter the email for recovery
    private String email() {
        TextInputDialog email = new TextInputDialog();
        email.setHeaderText("enter the email");
        email.setContentText("email");
        email.setTitle("recovery the pass ");
        email.showAndWait();
        return email.getResult();
    }

    // enter the username for recovery
    private String username() {
        TextInputDialog username = new TextInputDialog();
        username.setHeaderText("enter the username");
        username.setContentText("username");
        username.setTitle("recovery the username ");
        username.showAndWait();
        return username.getResult();
    }

    // search the input in the passenger list
    private boolean passengerRecovery(String email, String username) {
        PassengerRepository passengerRepository = new PassengerRepository();
        List<passenger> passengerList = passengerRepository.passengerList();
        boolean find = false;
        for (int i = 0; i < passengerList.size(); i++) {
            if (passengerList.get(i).getEmail().equals(email) && passengerList.get(i).getUsername().equals(username)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "ur password ==>>   " + passengerList.get(i).getPassword(), ButtonType.CLOSE);
                alert.showAndWait();
                find = true;
            }
        }
        return find;

    }

    // search the input in the employee list
    private boolean employeeRecovery(String email, String username) {
        UserRepository userRepository = new UserRepository();
        List<employee> employees = userRepository.employer();
        boolean find = false;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmail().equals(email) && employees.get(i).getUsername().equals(username)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "ur password ==>>   " + employees.get(i).getPassword(), ButtonType.CLOSE);
                alert.showAndWait();
                find = true;
            }
        }
        return find;

    }

    // after recovery the password ==> should load the login page
    private void loginLoader() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Login.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.getRoot()));
        stage.show();
    }


}
