package Database;

import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://"
                +dbHost+":"
                +dbPort+"/"
                +dbName;
        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser,dbPass);
        return dbConnection;
    }

    //Write
    public void signUpUser(User user){
        String insert = "INSERT INTO "
                +Const.USERS_TABLE+"("
                +Const.USERS_FIRSTNAME+","
                +Const.USERS_LASTNAME+","
                +Const.USERS_USERNAME+","
                +Const.USER_EMAIL+","
                +Const.USERS_PASSWORD+","
                +Const.USERS_LOCATION+")"+"VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3,user.getUserName());
            preparedStatement.setString(4,user.geteMail());
            preparedStatement.setString(5,user.getPassword());
            preparedStatement.setString(6,user.getLocation());

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Read

    //Update

    //Delete
}
