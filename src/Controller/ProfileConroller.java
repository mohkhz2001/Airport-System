package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileConroller implements Initializable {

    @FXML
    ImageView personImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        personImage.setImage(new Image("file:Icons/person.png"));
    }
}
