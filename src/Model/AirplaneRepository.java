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

}
