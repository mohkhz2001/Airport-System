package Model;

import java.sql.*;


public class Connection {

    private static final String DB_NAME = "airportSystemInfo.db";
    private static final String DB_DIRECTORY = "jdbc:sqlite:" + DB_NAME;


    public java.sql.Connection  connection() {
        java.sql.Connection connection;
        try {
            connection =  DriverManager.getConnection(DB_DIRECTORY);
            return connection;
        } catch (SQLException throwables) {
            System.out.println("there's problem in user Ripo connection....\n" + throwables);
            return null;
        }
    }

}
