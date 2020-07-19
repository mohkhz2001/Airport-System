package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository {

    Connection connection = new Connection();

    public List<Ticket> ticketList() {

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
                ticket.setPassID(resultSet.getString(Tables.TABLE_TICKET_LIST_PASS_ID));
                ticket.setFlightNumber(resultSet.getString(Tables.TABLE_TICKET_LIST_FLIGHT_NUMBER));
                tickets.add(ticket);
            }
            return tickets;

        } catch (SQLException throwables) {
            System.out.println("there's problem to executeQuery in pass repo...\n" + throwables);
            return null;
        }

    }

    public void TicketAdder(String passID, String flightNumber, int price, String ticketID) {

        try {
            Statement statement = connection.connection().createStatement();
            statement.execute("INSERT INTO " + Tables.TABLE_TICKET_LIST + "(" + Tables.TABLE_TICKET_LIST_PASS_ID
                    + " , " + Tables.TABLE_TICKET_LIST_FLIGHT_NUMBER + " , "
                    + Tables.TABLE_TICKET_LIST_TICKET_ID + " )" +
                    "VALUES ( '" + passID + "' , '" + flightNumber + "' , '" + ticketID + "'  )");

        } catch (SQLException e) {
            System.out.println("Error in add the ticket \n" + e);

        }
    }

    public boolean removeTicket(String FN) {
        Statement statement;

        try {
            statement = connection.connection().createStatement();
            statement.execute("DELETE FROM " + Tables.TABLE_TICKET_LIST + " WHERE " + Tables.TABLE_TICKET_LIST_FLIGHT_NUMBER+ "='" + FN + "'");
            return true;
        } catch (SQLException throwables) {
            System.out.println("there's problem to remove \n" + throwables);
            return false;
        }
    }

    public boolean cancelTicket(String Id) {
        Statement statement;

        try {
            statement = connection.connection().createStatement();
            statement.execute("DELETE FROM " + Tables.TABLE_TICKET_LIST + " WHERE " + Tables.TABLE_TICKET_LIST_FLIGHT_NUMBER+ "='" + Id + "'");
            return true;
        } catch (SQLException throwables) {
            System.out.println("there's problem to remove \n" + throwables);
            return false;
        }
    }

}
