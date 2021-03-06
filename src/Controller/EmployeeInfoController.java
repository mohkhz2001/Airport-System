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
    private String userID;
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
    @FXML
    Button changJob;

    /*
        if anythings changed of the info the exit btn will be the changed to updateBTN
        if not ==> if u press them will close the page
     */
    public void exitBTN() {
        if (isCheck()) {// if anythings changed
            /*
            at first get the confirmation that you want to update this gy
            if push yes=> we send info to the table ==> if update secsuusfully ==> show the confirmation its done/ if update not secsuusfully => show the error
            if push no => windows will be close
             */
            Alert alert = new Alert(Alert.AlertType.WARNING, "change " + " ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                boolean update = userRepository.updateEmployee(idField.getText(), jobField.getText(), Long.toString(salary));
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


        }
        //if nothings things changed
        else {
            ((Stage) exitBTN.getScene().getWindow()).close();
        }
    }

    // handel the fired btn for the employee
    public void firedBTN() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "do you really want to fired this employee??!", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            boolean remove = userRepository.removeEmployee(getUserID());
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

    // just super admin can change the usr job.....
    public void changJob() {
        if (jobChangeCheck().equals(Person.Job.superAdmin)){
            if (jobField.getText().equals(String.valueOf(Person.Job.Employee))){
                jobField.setText(String.valueOf(Person.Job.Management));
            }else if (jobField.getText().equals(String.valueOf(Person.Job.Management))){
                jobField.setText(String.valueOf(Person.Job.Employee));
            }
            changeBTN();
        }
    }

    // decease the salary
    public void decreaseSalaryBTN() {
        Long a = getSalary();
        setSalary(getSalary() - 500);
        salaryField.setText(setSalaryField(a - 500));
        changeBTN();
    }

    // increase the salary
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

    //set the field value
    public void SetField(employee employee) {
        nameField.setText(employee.getFirstName() + " " + employee.getLastName());

        idField.setText(employee.getID());
        jobField.setText(String.valueOf(employee.getJob()));
        addressField.setText(employee.getAddress());
        setSalary(Long.parseLong(employee.getSalary()));
        salaryField.setText(setSalaryField(getSalary()));
        emailField.setText(employee.getEmail());
        phoneNumberField.setText(employee.getPhoneNumber());

        setUserID(employee.getID());
    }

    // if the changed the info EXP(salary) ==> the exit BTN will be changed to update BTN
    private void changeBTN() {
        exitBTN.setText("update");
        setCheck(true);
    }

    // set the salary filed like this 1,200
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
    // return the job to  check the job
    private Person.Job jobChangeCheck() {
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isCheck() {
        return check;
    }

    // if changed the field...
    public void setCheck(boolean check) {
        this.check = check;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
