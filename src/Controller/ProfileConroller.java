package Controller;

import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileConroller implements Initializable {
    private String ID;


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
    Button conffirmBTN;
    @FXML
    Label rePassLBL;
    @FXML
    TableColumn<String, Flight> flightNumberColumn;
    @FXML
    TableColumn<String, Flight> dateColumn;
    @FXML
    TableColumn<String, Flight> hoursColumn;
    @FXML
    TableColumn<String, Flight> priceColumn;
    @FXML
    TableView<Flight> ticketListTable;

    public void passwordField() {
        passwordField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: green ; -fx-background-color: transparent");
        rePasswordField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: green ; -fx-background-color: transparent");
        conffirmBTN.setDisable(false);

        rePassLBL.setText("re-password");

    }

    public void emailField() {
        emailField.setStyle("-fx-border-width: 0 0 3 0 ; -fx-border-color: green ; -fx-background-color: transparent");
        conffirmBTN.setDisable(false);

    }

    public void conffirmBTN() {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        personImage.setImage(new Image("file:Icons/person.png"));
//        show();

    }

    public void TableShow() {

        flightNumberColumn.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("hours"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TicketRepository ticketRepository = new TicketRepository();
        List<Ticket> tickets = ticketRepository.ticketList();

        FlightRepository flightRepository = new FlightRepository();
        List<Flight> flights = flightRepository.flightList();

        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getPassID().equals(getID())) {
                for (int j = 0; j <flights.size() ; j++) {
                    if (flights.get(j).getFlightNumber().equals(tickets.get(i).getFlightNumber())){
                        ticketListTable.getItems().addAll(flights.get(i));
                    }
                }
            }
        }
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

            }
        }
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
