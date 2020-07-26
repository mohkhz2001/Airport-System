package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeBTNController implements Initializable {

    private String ID;

    @FXML
    Button ListBTN;
    @FXML
    Button newBTN;
    @FXML
    SplitPane splite;

    public void ListBTN() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/employeeList.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        employeeListController employeeListController = loader.getController();
        employeeListController.setID(getID());
        employeeListController.TableShow();
        employeeListController.TableAction();

        splite.getItems().set(1, loader.getRoot());

    }

    public void newBTN() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/NewEmployee.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        splite.getItems().set(1, loader.getRoot());


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ListBTN.setGraphic(new ImageView("file:Icons/List.png"));
        newBTN.setGraphic(new ImageView("file:Icons/newEmployee.png"));

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
