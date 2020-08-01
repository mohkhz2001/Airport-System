package Controller;

import Model.PassengerRepository;
import Model.passenger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.List;
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
    Button supportBTN;
    @FXML
    AnchorPane pane;
    @FXML
    SplitPane split;
    @FXML
    Label userNameLBL;
    @FXML
    Label timeLBL;
    @FXML
    ImageView personTwoView;


    public void supportBTN() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PassengerSupport.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(loader.getRoot()));
        stage.show();

    }

    public void supportBTNEnter() {
        supportBTN.setStyle("-fx-background-color: darkturquoise");
    }

    public void supportBTNExit() {
        supportBTN.setStyle("-fx-background-color:  #005eff ");
    }

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

    public void getMoneyBTN() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MoneyPay.fxml"));
        try {
            loader.load();
            MoneyPayController moneyPayController = loader.getController();
            moneyPayController.setID(getID());
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
        personTwoView.setImage(new Image("file:Icons/personTwo.png"));
        supportBTN.setGraphic(new ImageView("file:Icons/support.png"));

        showTime();

    }

    public String getID() {
        return ID;
    }

    private void showTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            timeLBL.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setUserNameLBL(String ID) {
        PassengerRepository passengerRepository = new PassengerRepository();
        List<passenger> passengerList = passengerRepository.passengerList();
        for (int i = 0; i < passengerList.size(); i++) {
            if (passengerList.get(i).getID().equals(ID)) {
                userNameLBL.setText(passengerList.get(i).getFirstName() + " " + passengerList.get(i).getLastName());
                break;
            }
        }
    }
}
