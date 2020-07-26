package Controller;

import Model.Airplane;
import Model.UserRepository;
import Model.employee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class employeeListController implements Initializable {
    UserRepository userRepository = new UserRepository();

    private String ID;

    @FXML
    TableView<employee> List;
    @FXML
    TableColumn<String, employee> idColumn;
    @FXML
    TableColumn<String, employee> firstNameColumn;
    @FXML
    TableColumn<String, employee> lastNameColumn;
    @FXML
    TableColumn<String, employee> emailColumn;
    @FXML
    TableColumn<String, employee> salaryColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List.setStyle("/resource/TableView.css");
    }

    public void TableShow() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        java.util.List<employee> employees = userRepository.employer();

        for (int i = 0; i < employees.size(); i++) {
            if (!employees.get(i).getID().equals(getID()))
                List.getItems().addAll(employees.get(i));
        }
    }

    public void TableAction() {
        List.setRowFactory(tv -> {
            TableRow<employee> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

                    employee clickedRow = row.getItem();
                    loaderInfo(clickedRow);
                }

            });
            return row;
        });
    }

    private void loaderInfo(employee employee){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/EmployeeInfo.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EmployeeInfoController employeeInfoController = loader.getController();
        employeeInfoController.SetField(employee);
        employeeInfoController.setID(employee.getID());
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.getRoot()));
        stage.show();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}