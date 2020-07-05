package Controller;

import Model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class PassengerListController implements Initializable {
    TicketRepository ticketRepository = new TicketRepository();
    PassengerRepository passengerRepository = new PassengerRepository();

    @FXML
    TableColumn<String, passenger> userIdColumn;
    @FXML
    TableColumn<String, passenger> lastNameColumn;
    @FXML
    TableColumn<String, passenger> firstNameColumn;
    @FXML
    TableColumn<String, Ticket> ticketIDColumn;
    @FXML
    TableColumn<String, passenger> emailColumn;
    @FXML
    TableView<passenger> passengerTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void TableShow(Flight info) {
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        ticketIDColumn.setCellValueFactory(new PropertyValueFactory<>("ticketId"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        List<Ticket> tickets = ticketRepository.ticketList();
        List<passenger> passengers = passengerRepository.passengerList();

        for (int i = 0; i < tickets.size(); i++) {

            if (tickets.get(i).getFlightNumber().equals(info.getFlightNumber())) {

                for (int j = 0; j < passengers.size(); j++) {

                    if (passengers.get(j).getID().equals(tickets.get(i).getPassID())) {
//                        ticketIDColumn.getColumns().addAll((Collection<? extends TableColumn<String, ?>>) tickets.get(i));
                        passengerTable.getItems().addAll(passengers.get(j));


                    }
                }


            }
        }

    }
}
