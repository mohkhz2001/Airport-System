package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PassengerController implements Initializable {
    private String ID;

    @FXML
    Button exitBTN;
    @FXML
    Button getMoneyBTN;
    @FXML
    Button ticketBTN;
    @FXML
    Button profileBTN;
    @FXML
    Button criticismBTN;
    @FXML
    AnchorPane pane;
    @FXML
    SplitPane split;

    public void exitBTN() {
        ((Stage) exitBTN.getScene().getWindow()).close();
    }

    public void ticketBTN(ActionEvent e) {

        ticketBTN.setStyle("-fx-background-color: darkturquoise");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/TicketList.fxml"));

        try {
            loader.load();
            split.getItems().set(1, loader.getRoot());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        TicketListController ticketListController = loader.getController();
        ticketListController.setID(getID());

    }

    public void profileBTN() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Profile.fxml"));
        try {
            loader.load();
            ProfileConroller profileConroller = loader.getController();
//            System.out.println(getID());
            profileConroller.show(getID());
            profileConroller.TableShow();
            split.getItems().set(1, loader.getRoot());
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void criticismBTN() {
    }

    public void getMoneyBTN() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MoneyPay.fxml"));
        try {
            loader.load();

            split.getItems().set(1, loader.getRoot());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void profileBTNEnter() {
        profileBTN.setStyle("-fx-background-color: darkturquoise");
    }

    public void profileBTNExit() {
        profileBTN.setStyle("-fx-background-color: #005eff ");
    }

    public void exitBTNEnter() {
        exitBTN.setStyle("-fx-background-color: red ");
//        exitBTN.setGraphic(null);
//        exitBTN.setText("exit");
    }

    public void exitBTNExit() {
        exitBTN.setStyle("-fx-background-color:  #005eff ");
//        exitBTN.setGraphic(new ImageView("file:Icons/exit.png"));
//        exitBTN.setText("");
    }

    public void ticketBTNEnter() {
        ticketBTN.setStyle("-fx-background-color: darkturquoise");
    }

    public void ticketBTNExit() {
        ticketBTN.setStyle("-fx-background-color:  #005eff ");
    }

    public void criticismBTNEnter() {
        criticismBTN.setStyle("-fx-background-color: darkturquoise");
    }

    public void criticismBTNExit() {
        criticismBTN.setStyle("-fx-background-color:  #005eff ");
    }

    public void getMoneyBTNEnter() {
        getMoneyBTN.setStyle("-fx-background-color: darkturquoise");
    }

    public void getMoneyBTNExit() {
        getMoneyBTN.setStyle("-fx-background-color:  #005eff ");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        exitBTN.setGraphic(new ImageView("file:Icons/exit.png"));
        getMoneyBTN.setGraphic(new ImageView("file:Icons/get-money.png"));
        ticketBTN.setGraphic(new ImageView("file:Icons/ticket.png"));
        profileBTN.setGraphic(new ImageView("file:Icons/profile.png"));
        criticismBTN.setGraphic(new ImageView("file:Icons/criticism.png"));

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
