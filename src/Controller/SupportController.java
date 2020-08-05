package Controller;

import Model.Flight;
import Model.Support;
import Model.SupportRepository;
import Model.employee;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class SupportController implements Initializable {

    int counter;

    @FXML
    TableColumn<Integer, Support> numberColumn;
    @FXML
    TableColumn<String, Support> ideaColumn;
    @FXML
    TableColumn<String, Support> readColumn;
    @FXML
    TableView<Support> List;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // show the table
    public void TableShow() {

        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        ideaColumn.setCellValueFactory(new PropertyValueFactory<>("idea"));


        SupportRepository supports = new SupportRepository();
        List<Support> support = supports.supports();


        for (int i = 0; i < support.size(); i++) {
            List.getItems().addAll(support.get(i));

        }
    }


}
