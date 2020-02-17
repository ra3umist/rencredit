package database;

import com.sun.org.apache.bcel.internal.ExceptionConst;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataBaseManager {
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/MySql";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DataBaseManager instance;

    private  Connection connection;

    private DataBaseManager() {

    }

    public static DataBaseManager getInstance() {
        if (instance==null)
            instance = new DataBaseManager();
        return instance;
    }

    public Connection getConnection()  {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    public void writeStartTime(Date startTime, String stepName) {
        try {
            String sql = "INSERT INTO rencredit.steps_info(step_name, start_time) Values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, stepName);
            preparedStatement.setString(2, simpleDateFormat.format(startTime));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        }
    }

    public void writeEndTime(Date endTime, String stepName) {
        try {
            String sql = "UPDATE rencredit.steps_info SET end_time = (?) WHERE step_name = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, simpleDateFormat.format(endTime));
            preparedStatement.setString(2, stepName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
