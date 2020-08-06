package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class managerController implements Initializable {
    // this page its a controller of the main menu for the manager
    private String ID;
    private Button exitBTN;

    @FXML
    HBox hbox;
    @FXML
    SplitPane split;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // if employee login
    public void employee(String ID) {
        Button profileBTN = new Button();
        Button flightManagerBTN = new Button();
        exitBTN = new Button();

        profileBTNSetup(profileBTN);
        profileBTNAction(profileBTN);

        flightManagerSetup(flightManagerBTN);
        flightManagerBTNAction(flightManagerBTN);

        exitBTNSetup(exitBTN);
        exitBTNAction(exitBTN);

        hbox.getChildren().addAll(profileBTN, flightManagerBTN, exitBTN);

    }

    // if management login   (have the employee Possibilities too)
    public void manager(String ID) {
        employee(ID);

        Button employee = new Button();
        employeeBTNAction(employee);
        employeeBTNSetup(employee);
        employee.setGraphic(new ImageView("file:Icons/employee.png"));

        Button support = new Button();
        employeeBTNSetup(support);
        supportBTNAction(support);
        support.setGraphic(new ImageView("file:Icons/support.png"));

        hbox.getChildren().set(2, employee);

        hbox.getChildren().addAll(support, exitBTN);

    }

    //if super admin login (have the management & employee Possibilities too)
    public void superAdmin(String ID) {
        manager(ID);

    }

    // exit btn action
    private void exitBTNAction(Button exitBTN) {

        exitBTN.setOnMouseClicked(event -> {
            ((Stage) exitBTN.getScene().getWindow()).close();
        });
    }

    // setup exit  btn (set style && the icon)
    private void exitBTNSetup(Button exitBTN) {
        exitBTN.getStylesheets().add("/resource/exitBTN.css");
        exitBTN.setGraphic(new ImageView("file:Icons/exit.png"));

    }

    // setup flight manager btn (set style && the icon)
    private void flightManagerSetup(Button flightManagerBTN) {
        flightManagerBTN.getStylesheets().add("/resource/menuBTN.css");
        flightManagerBTN.setGraphic(new ImageView("file:Icons/flight manage.png"));
    }

    // set the action for the flight manager btn
    private void flightManagerBTNAction(Button flightManagerBTN) {

        flightManagerBTN.setOnMouseClicked(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/FlightManager.fxml"));

            try {
                loader.load();
                split.getItems().set(1, loader.getRoot());

            } catch (IOException e) {
                e.printStackTrace();
            }

            FlightManagerController flightManagerController = loader.getController();
            flightManagerController.setID(getID());
            flightManagerController.getJob();
            flightManagerController.management();

        });

    }

    // setup exit  btn (set style && the icon)
    private void profileBTNSetup(Button profileBTN) {
        profileBTN.getStylesheets().add("/resource/menuBTN.css");
        profileBTN.setGraphic(new ImageView("file:Icons/profile.png"));
    }

    // set the action for the profile btn
    private void profileBTNAction(Button profileBTN) {

        profileBTN.setOnMouseClicked(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/EmployeeProfile.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            EmployeeProfileController employeeProfileController = loader.getController();
            employeeProfileController.setID(getID());
            employeeProfileController.fillField();
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            stage.show();

        });
    }

    // set the action for the for the employee btn
    private void employeeBTNAction(Button employeeBTN) {
        employeeBTN.setOnMouseClicked(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/EmployeeBTN.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            EmployeeBTNController employeeBTNController = loader.getController();
            employeeBTNController.setID(getID());

            split.getItems().set(1, loader.getRoot());

        });

    }

    // set the action for the support btn
    private void supportBTNAction(Button support) {
        support.setOnMouseClicked(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Support.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SupportController supportController = loader.getController();
            supportController.TableShow();

            split.getItems().set(1, loader.getRoot());
        });
    }

    // setup exit  btn (set style && the icon)
    private void employeeBTNSetup(Button employeeBTN) {
        employeeBTN.getStylesheets().add("/resource/menuBTN.css");
//        employeeBTN.setGraphic(new ImageView("file:Icons/exit.png"));

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}