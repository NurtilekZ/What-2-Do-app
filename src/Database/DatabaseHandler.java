package Database;

import model.Task;
import model.User;

import java.sql.*;

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

    public ResultSet getUser(User user){
        ResultSet resultSet = null;
        if (!user.getUserName().equals("") || !user.getPassword().equals("")) {
            String query = "SELECT * FROM "
                    +Const.USERS_TABLE +" WHERE "
                    +Const.USERS_USERNAME+"=?"+" AND "
                    +Const.USERS_PASSWORD+"=?";
            //select all from users where username and password exists.

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1,user.getUserName());
                preparedStatement.setString(2,user.getPassword());

                resultSet = preparedStatement.executeQuery();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please enter your credentials");
        }
        return resultSet;
    }

    public int getAllTasks(int userId) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM "+Const.TASKS_TABLE+" WHERE "+Const.USERS_USERID+"=?";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            return resultSet.getInt(1);
        }
        return resultSet.getInt(1);
    }

    public void insertTask(Task task){
        String insert = "INSERT INTO "
                +Const.TASKS_TABLE+"("
                +Const.USERS_USERID+","
                +Const.TASKS_TASK+","
                +Const.TASKS_DATE+","
                +Const.TASK_LOCATION+","
                +Const.TASKS_NOTES+")"+"VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setInt(1, task.getUserId());
            preparedStatement.setString(2,task.getTask());
            preparedStatement.setDate(3,task.getDate());
            preparedStatement.setString(4,task.getLocation());
            preparedStatement.setString(5,task.getNotes());

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Read

    //Update

    //Delete
}
