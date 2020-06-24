package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository {

    Connection connection = new Connection();

    public List<Ticket> ticketList(){

        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.connection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + Tables.TABLE_TICKET_LIST);
//            System.out.println("Done");
            ArrayList<Ticket> tickets = new ArrayList<>();

            while (resultSet.next()) {
               Ticket ticket = new Ticket();
               ticket.setID(resultSet.getString(Tables.TABLE_TICKET_LIST_TICKET_ID));
               ticket.setPassID(resultSet.getInt(Tables.TABLE_TICKET_LIST_PASS_ID));
               ticket.setFlightNumber(resultSet.getString(Tables.TABLE_TICKET_LIST_FLIGHT_NUMBER));

                tickets.add(ticket);
            }
            return tickets;

        } catch (SQLException throwables) {
            System.out.println("there's problem to executeQuery in pass repo...\n" + throwables);
            return null;
        }

    }

}
