package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AirplaneRepository {

    Connection connection = new Connection();

    public List<Airplane> airplaneList() {
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.connection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + Tables.TABLE_AIRPLANE);

            ArrayList<Airplane> flightArrayList = new ArrayList<>();
            while (resultSet.next()) {

                Airplane airplane = new Airplane();

                airplane.setRegister(resultSet.getString(Tables.TABLE_AIRPLANE_REGISTER));
                airplane.setType(resultSet.getString(Tables.TABLE_AIRPLANE_TYPE));
                airplane.setSeats(resultSet.getInt(Tables.TABLE_AIRPLANE_SEAT));

                flightArrayList.add(airplane);
            }

            return flightArrayList;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public boolean addNewAirplane(String register, String type, String seats) {
        try {
            Statement statement = connection.connection().createStatement();
            statement.execute("INSERT INTO " + Tables.TABLE_AIRPLANE + "(" + Tables.TABLE_AIRPLANE_REGISTER
                    + " , " + Tables.TABLE_AIRPLANE_TYPE + " , "
                    + Tables.TABLE_AIRPLANE_SEAT + " )" +
                    "VALUES ( '" + register + "' , '" + type + "' , '" + seats + "'  )");
            return true;
        } catch (SQLException e) {
            System.out.println("Error in add the new Airplane \n" + e);
            return false;
        }
    }

    public boolean removeAirplane(String register) {
        Statement statement;

        try {
            statement = connection.connection().createStatement();
            statement.execute("DELETE FROM " + Tables.TABLE_AIRPLANE + " WHERE " + Tables.TABLE_AIRPLANE_REGISTER + "='" + register + "'");
            return true;
        } catch (SQLException throwables) {
            System.out.println("there's problem to remove \n" + throwables);
            return false;
        }
    }

    public boolean airplaneUpdate(String register, String type, String seats) {
        try {
            Statement statement = connection.connection().createStatement();
            statement.execute("UPDATE " + Tables.TABLE_AIRPLANE+ " SET " + Tables.TABLE_AIRPLANE_TYPE + "='" + type + "'WHERE " + Tables.TABLE_AIRPLANE_REGISTER + "='" + register+ "'" );
            statement.execute("UPDATE " + Tables.TABLE_AIRPLANE + " SET " + Tables.TABLE_AIRPLANE_SEAT + "='" + seats + "'WHERE " + Tables.TABLE_AIRPLANE_REGISTER + "='" + register+ "'" );
            return true;
        } catch (SQLException e) {
            System.out.println("error in update the passenger information" + e);
            return false;
        }
    }
}
