package Controller;

import Model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileConroller implements Initializable {
    TicketRepository ticketRepository = new TicketRepository();
    FlightRepository flightRepository = new FlightRepository();
    PassengerRepository passengerRepository = new PassengerRepository();

    private String ID;
    // change1 => for change the pass field , change2 => will be true by change the email field or
    boolean change1 = false, change2 = false;


    @FXML
    ImageView personImage;
    @FXML
    TextField fullNameField;
    @FXML
    TextField idField;
    @FXML
    PasswordField passwordField;
    @FXML
    PasswordField rePasswordField;
    @FXML
    TextField jobField;
    @FXML
    TextField emailField;
    @FXML
    TextField cashField;
    @FXML
    Button conffirmBTN;
    @FXML
    Label rePassLBL;
    @FXML
    TableColumn<Flight, String> flightNumberColumn;
    @FXML
    TableColumn<Flight, String> dateColumn;
    @FXML
    TableColumn<Flight, String> hoursColumn;
    @FXML
    TableView<Flight> ticketListTable;

    public void passwordField() {

        passwordField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: green ; -fx-background-color: transparent");
        rePasswordField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: green ; -fx-background-color: transparent");
        conffirmBTN.setDisable(false);
        change1 = true;
        rePassLBL.setText("re-password");
        rePasswordField.setDisable(false);
        rePasswordField.setEditable(true);


    }

    public void emailField() {

        emailField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: green ; -fx-background-color: transparent");
        conffirmBTN.setDisable(false);
        change2 = true;

    }

    public void conffirmBTN() {

        if (change1 || change2 || (change1 && change2)) {

            PassengerRepository passengerRepository = new PassengerRepository();

            boolean update = passengerRepository.PassengerUpdate(emailField.getText(), passwordField.getText(), getID());
            if (update) {
                JOptionPane.showMessageDialog(null, "successfully changed ;) ");
                emailField.setStyle("-fx-border-color: grey ; -fx-border-width: 0 0 3 0 ; -fx-background-color: transparent");
                passwordField.setStyle("-fx-border-color: grey ; -fx-border-width: 0 0 3 0 ; -fx-background-color: transparent");
                rePasswordField.setStyle(" -fx-background-color: transparent");
                rePasswordField.setText(null);
                rePassLBL.setText(null);

            }
        }

    }

    public void conffirmBTNEnter() {
        conffirmBTN.setStyle("-fx-background-color: #80c800");
    }

    public void conffirmBTNExit() {
        conffirmBTN.setStyle("-fx-background-color: aliceblue,\n" +
                "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%)");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        personImage.setImage(new Image("file:Icons/person.png"));
    }
    // set value for table(show the person ticket)
    public void TableShow() {

        flightNumberColumn.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("hours"));

        List<Ticket> tickets = ticketRepository.ticketList();

        List<Flight> flights = flightRepository.flightList();

        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getPassID().equals(getID())) {
                for (int j = 0; j < flights.size(); j++) {
                    if (flights.get(j).getFlightNumber().equals(tickets.get(i).getFlightNumber())) {
                        ticketListTable.getItems().addAll(flights.get(j));
                    }
                }
            }
        }

        // add btn to table
        addButtonToTable();
    }

    public void show(String ID) {
        this.ID = ID;
//        System.out.println(getID());

        PassengerRepository passengerRepository = new PassengerRepository();
        List<passenger> passengerList = passengerRepository.passengerList();

        for (int i = 0; i < passengerList.size(); i++) {

            if (passengerList.get(i).getID().equals(getID())) {

                fullNameField.setText(passengerList.get(i).getFirstName() + " " + passengerList.get(i).getLastName());
                idField.setText(passengerList.get(i).getID());
                passwordField.setText(passengerList.get(i).getPassword());
                emailField.setText(passengerList.get(i).getEmail());
                jobField.setText(String.valueOf(passengerList.get(i).getJob()));
                cashField.setText(passengerList.get(i).getMoney());

            }
        }
//        addButtonToTable();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    private void addButtonToTable() {
        TableColumn<Flight, Void> colBtn = new TableColumn("Button Column");

        Callback<TableColumn<Flight, Void>, TableCell<Flight, Void>> cellFactory = new Callback<TableColumn<Flight, Void>, TableCell<Flight, Void>>() {
            @Override
            public TableCell<Flight, Void> call(final TableColumn<Flight, Void> param) {
                final TableCell<Flight, Void> cell = new TableCell<Flight, Void>() {

                    private final Button btn = new Button("cancel");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Flight flight = getTableView().getItems().get(getIndex());
                            confirmationForCancel(flight.getFlightNumber());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        ticketListTable.getColumns().add(colBtn);

    }

    private void confirmationForCancel(String FN) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "cancel " + FN + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING, "just will be give you 90 percent of the ticket price ", ButtonType.APPLY, ButtonType.CANCEL);
            alert1.showAndWait();
            if (alert1.getResult() == ButtonType.APPLY) {
                cancelation(FN);
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.OK);
                alert2.showAndWait();
            } else {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "cancelation canceled ;)", ButtonType.OK);
                alert2.showAndWait();
            }

        } else {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "cancelation canceled ;)", ButtonType.OK);
            alert1.showAndWait();
        }

    }

    public void cancelation(String FN) {
        List<Flight> flights = flightRepository.flightList();
        List<Ticket> tickets = ticketRepository.ticketList();
        int money = 0;
        //increase capacity
        for (int i = 0; i < flights.size(); i++) {

            if (flights.get(i).getFlightNumber().equals(FN)) {
                flightRepository.capacityCorrection(flights.get(i).getCapacity() + 1, FN);
                money = flights.get(i).getPrice();
                break;
            }

        }
        // remove the ticket
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getFlightNumber().equals(FN)) {
                ticketRepository.cancelTicket(tickets.get(i).getID());
                break;
            }
        }
        // increase the money for person
        passengerRepository.increaseMoney(Integer.toString(money * 9 / 10), getID());
    }
}
