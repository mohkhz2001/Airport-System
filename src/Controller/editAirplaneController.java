package Controller;

import Model.Airplane;
import Model.AirplaneRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class editAirplaneController implements Initializable {

    AirplaneRepository airplaneRepository = new AirplaneRepository();


    @FXML
    TextField registerField;
    @FXML
    TextField typeField;
    @FXML
    TextField seatsField;
    @FXML
    Button submitBTN;
    @FXML
    ImageView image;


    public void submitBTN() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "update the airplane", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES && (!registerField.getText().isEmpty() && !typeField.getText().isEmpty() && !seatsField.getText().isEmpty())) {
            boolean update = airplaneRepository.airplaneUpdate(registerField.getText(), typeField.getText(), seatsField.getText());
            if (update) {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.OK);
                alert1.showAndWait();
                ((Stage) submitBTN.getScene().getWindow()).close();
            } else {
                Alert alert1 = new Alert(Alert.AlertType.ERROR, "can't update....", ButtonType.OK);
                alert1.showAndWait();
            }

        }
        // if pressed no
        else if (alert.getResult() == ButtonType.NO) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "update cancelled....", ButtonType.OK);
            alert1.showAndWait();
        }
        // if fields are empty....
        else {
            setFieldBoarder();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image.setImage(new Image("file:Icons/airplane1.png"));
    }

    public void setFields(Airplane airplane) {
        registerField.setText(airplane.getRegister());
        typeField.setText(airplane.getType());
        seatsField.setText(Integer.toString(airplane.getSeats()));
    }

    private void setFieldBoarder() { // this function work if the field are empty .......
        if (typeField.getText().isEmpty()) {
            typeField.setStyle("-fx-border-width: 1 1 3 1; -fx-border-color: red ; -fx-background-color: transparent");
        }
        if (seatsField.getText().isEmpty()) {
            seatsField.setStyle("-fx-border-width: 1 1 3 1; -fx-border-color: red ; -fx-background-color: transparent");
        }
    }

}
