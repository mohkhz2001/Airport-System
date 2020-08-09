package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReportRepository {

    Connection connection = new Connection();


    public List<Report> reportList() {
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.connection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + Tables.TABLE_REPORT);
//            System.out.println("Done");
            ArrayList<Report> reports = new ArrayList<>();

            while (resultSet.next()) {
                Report report = new Report();
                report.setID(resultSet.getString(Tables.TABLE_REPORT_ID));
                report.setDate(resultSet.getString(Tables.TABLE_REPORT_DATE));
                report.setTime(resultSet.getString(Tables.TABLE_REPORT_TIME));
                report.setText(resultSet.getString(Tables.TABLE_REPORT_TEXT));

                reports.add(report);
            }
            return reports;

        } catch (SQLException throwables) {
            System.out.println("there's problem to executeQuery in pass repo...\n" + throwables);
            return null;
        }


    }

    public boolean reportAdd(String ID, String date, String time, String text) {
        try {
            Statement statement = connection.connection().createStatement();
            statement.execute("INSERT INTO " + Tables.TABLE_REPORT + "(" + Tables.TABLE_REPORT_ID + " , " + Tables.TABLE_REPORT_DATE + " , " + Tables.TABLE_REPORT_TIME + " , "
                    + Tables.TABLE_REPORT_TEXT + " )" +
                    "VALUES ( '" + ID + "' , '" + date + "' , '" + time + "' , '" + text + "'  )");
            return true;
        } catch (SQLException e) {
            System.out.println("Error in add the new passenger " + e);
            return false;
        }
    }

}
