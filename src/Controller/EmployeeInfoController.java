package Controller;

import Model.Flight;
import Model.Person;
import Model.UserRepository;
import Model.employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeInfoController implements Initializable {
    UserRepository userRepository = new UserRepository();

    private String ID;
    private boolean check;
    private Long salary;


    @FXML
    TextField nameField;
    @FXML
    TextField idField;
    @FXML
    TextField jobField;
    @FXML
    TextField addressField;
    @FXML
    TextField salaryField;
    @FXML
    TextField emailField;
    @FXML
    TextField phoneNumberField;
    @FXML
    ImageView personImage;
    @FXML
    Button exitBTN;
    @FXML
    Button firedBTN;
    @FXML
    Button decreaseSalaryBTN;
    @FXML
    Button increaseSalaryBTN;


    public void exitBTN() {
        if (isCheck()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "change " + " ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                boolean update = userRepository.updateEmployee(idField.getText(), jobField.getText(), salaryField.getText());
                Alert alert1;
                if (update) {
                    alert1 = new Alert(Alert.AlertType.CONFIRMATION, "done", ButtonType.CLOSE);
                    alert1.showAndWait();
                    ((Stage) exitBTN.getScene().getWindow()).close();
                } else {
                    alert1 = new Alert(Alert.AlertType.ERROR, "done", ButtonType.CLOSE);
                    alert1.showAndWait();
                }
            } else if (alert.getResult() == ButtonType.NO)
                ((Stage) exitBTN.getScene().getWindow()).close();


        } else {
            ((Stage) exitBTN.getScene().getWindow()).close();
        }
    }

    public void firedBTN() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "do you really want to fired this employee??!", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            boolean remove = userRepository.removeEmployee(getID());
            Alert alert1;
            if (remove) {
                alert1 = new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.OK);
                alert1.showAndWait();
                ((Stage) firedBTN.getScene().getWindow()).close();
            } else {
                alert1 = new Alert(Alert.AlertType.ERROR, "can't remove", ButtonType.OK);
                alert1.showAndWait();
            }
        } else {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "fired cancelled", ButtonType.OK);
            alert1.showAndWait();
        }
    }

    public void salaryFieldType() {
        salaryField.setStyle("-fx-background-color: transparent ; -fx-border-width: 0 0 3 0 ; -fx-border-color: green");
        changeBTN();
    }

    public void jobFieldType() {

    }

    public void decreaseSalaryBTN() {
        Long a = getSalary();
        setSalary(getSalary() - 500);
        salaryField.setText(setSalaryField(a - 500));
        changeBTN();
    }

    public void increaseSalaryBTN() {
        Long a = getSalary();
        setSalary(getSalary() + 500);
        salaryField.setText(setSalaryField(a + 500));
        changeBTN();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        personImage.setImage(new Image("file:Icons/person1.png"));
    }

    public void SetField(employee employee) {
        nameField.setText(employee.getFirstName() + " " + employee.getLastName());

        idField.setText(employee.getID());
        jobField.setText(String.valueOf(employee.getJob()));
        addressField.setText(employee.getAddress());
        setSalary(Long.parseLong(employee.getSalary()));
        salaryField.setText(setSalaryField(getSalary()));
        emailField.setText(employee.getEmail());
        phoneNumberField.setText(employee.getPhoneNumber());

    }

    private void changeBTN() {
        exitBTN.setText("update");
        setCheck(true);
    }

    private String setSalaryField(Long salary) {
        char[] a = Long.toString(salary).toCharArray();
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

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
