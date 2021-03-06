package Controller;

import Model.Airplane;
import Model.Person;
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
        // set the style for the table
        List.setStyle("/resource/TableView.css");
    }

    //  set the value for the table ==>  show the employee
    public void TableShow() {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        java.util.List<employee> employees = userRepository.employer();

        for (int i = 0; i < employees.size(); i++) {
            if (!employees.get(i).getID().equals(getID()))
                if (!employees.get(i).getJob().equals(job()) && !employees.get(i).getJob().equals(Person.Job.superAdmin))
                    List.getItems().addAll(employees.get(i));
        }
    }

    // search the user job and return them ==> why(beacuse dont show the user that In one category and just can see the lower category)
    private Person.Job job() {
        List<employee> employees = userRepository.employer();
        Person.Job job = null;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getID().equals(getID())) {
                job = employees.get(i).getJob();
                break;
            }
        }
        return job;
    }

    // set the table action ==> when double click on the user new stage will be open and show the user info
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

    // after click on the user ==> should load employee info page and set the field
    private void loaderInfo(employee employee) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/EmployeeInfo.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EmployeeInfoController employeeInfoController = loader.getController();
        employeeInfoController.SetField(employee);
        employeeInfoController.setID(getID());
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