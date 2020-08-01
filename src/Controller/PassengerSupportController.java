package Controller;

import Model.SupportRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PassengerSupportController implements Initializable {
    SupportRepository supportRepository = new SupportRepository();

    @FXML
    TextArea ideaArea;
    @FXML
    Label wordCounterLBL;
    @FXML
    Button sendBTN;

    // add the idea to DB to show them to manager
    public void sendBTN() {
        // add the idea to DB with addIdea function in the repo...
        boolean add = supportRepository.addIdea(ideaArea.getText());
        if (add) { // if the respons was true
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.CLOSE);
            alert.showAndWait();
        } else {// if the respons was false
            Alert alert = new Alert(Alert.AlertType.ERROR, "cant add", ButtonType.CLOSE);
            alert.showAndWait();
        }
        // close the page
        ((Stage) sendBTN.getScene().getWindow()).close();
    }

    // handel the number of the word
    public void ideaAreaTyped() {
        int n = Integer.parseInt(wordCounterLBL.getText());
        if (n != 0)// if word counter wasn't 0
            wordCounterLBL.setText(Integer.toString(n - 1));// decrease the word counter...
        else {// if were 0

        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set /n in automaticly
        ideaArea.setWrapText(true);
    }

}
