package com.swingy.database;

import com.swingy.input.KeyInput;
import com.swingy.rendering.entities.Fighter;
import com.swingy.view.Swingy;
import org.jetbrains.annotations.NotNull;

import javax.management.Query;
import javax.swing.*;
import java.sql.*;

public class SwingyDB{

    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL = "jdbc:derby:swingydb;create=true";
    private static final String SQL_SELECT = "select * from players";
    private static final String SQL_INSERT = "insert into players (name, xp, character_class, active) values";
    private static final String SQL_DELETE = "delete from players";
    private static final String SQL_CREATE = "create table players(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), name varchar(255), xp integer, character_class varchar (255), active boolean)";
    private static final String SQL_DROP = "drop table players";
    private static final String SQL_UPDATE = "update players set";
    private Statement statement;
    private Connection connection;
    private int rowCount;

    private boolean busy = false;

    public static SwingyDB swingyDB;

    static {
        try {
            swingyDB = new SwingyDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private SwingyDB() throws ClassNotFoundException{
        Class.forName(DB_DRIVER);
    }

    private boolean existDB() throws SQLException {
        createConnection();
        ResultSet res = connection.getMetaData().getTables(null, "APP", "PLAYERS", null);
        if (res.next()) {
            closeConnection();
            return true;
        } else {
            closeConnection();
            return false;
        }
    }

    public void createDB() throws SQLException {
        if (!busy) {
            busy = true;
            createConnection();
            if (existDB()) {
                System.out.println("SwingDB Already Exists");
                closeConnection();
            } else {
                connection.createStatement().execute(SQL_CREATE);
                System.out.println("SwingDB Successfully Created");
                closeConnection();
            }
            busy = false;
        }
    }

    public void dropDB() throws SQLException {
        if (!busy) {
            busy = true;
            if (existDB()) {
                createConnection();
                connection.createStatement().execute(SQL_DROP);
                System.out.println("SwingDB Successfully Dropped");
                closeConnection();
            } else {
                System.out.println("SwingDB Does Not Exist");
            }
            busy = false;
        }
    }

    public long insertPlayer(@NotNull Fighter fighter) throws SQLException {
        int toReturn = -1;
        if (!busy) {
            busy = true;
            createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT
                    + " ('" + fighter.getFighterMetrics().getName() + "'"
                    + "," + (int) fighter.getFighterMetrics().getLevel().getExperience()
                    + ",'" + fighter.getPlayerClassName() + "'"
                    + "," + true + ")", PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.execute();

            //Get Generated ID

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                System.out.println("Record Successfully Inserted with ID: " + resultSet.getInt(1));
                toReturn = resultSet.getInt(1);
            }
            closeConnection();
            busy = false;
        }
        return toReturn;
    }

    public void updatePlayer(@NotNull Fighter fighter) throws SQLException {
        if(!busy) {
            busy = true;
            createConnection();
            connection.createStatement().execute(SQL_UPDATE + " xp = " + fighter.getFighterMetrics().getLevel().getExperience() + " where id = " + fighter.getFighterMetrics().getID());
            closeConnection();
            busy = false;
        }
    }

    public ResultSet queryPlayer(long id) throws SQLException {
        ResultSet resultSet = null;
        if (!busy) {
            busy = true;
            createConnection();
            resultSet = statement.executeQuery(SQL_SELECT + " where id = " + id);
            busy = false;
        }
        return resultSet;
    }

    public ResultSet queryPlayer() throws SQLException {
        ResultSet resultSet = null;
        if (!busy) {
            busy = true;
            createConnection();
            resultSet = statement.executeQuery(SQL_SELECT + " where active = true");
            busy = false;
        }
        return resultSet;
    }

    public ResultSet queryAll() throws SQLException {
        ResultSet resultSet = null;
        if (!busy) {
            busy = true;
            createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            resultSet = preparedStatement.executeQuery();
            boolean b = resultSet.last();
            rowCount = resultSet.getRow();
            resultSet.beforeFirst();
            busy = false;
        }
        return resultSet;
    }

    public void setCurrentPlayer(long id) throws SQLException {
        if (!busy) {
            busy = true;
            createConnection();
            connection.createStatement().execute(SQL_UPDATE + " active = true where id = " + id);
            System.out.println("Player with ID : " + id + " Successfully Set To Active");
            closeConnection();
            busy = false;
        }
    }

    public void deletePlayer(long id) throws SQLException {
        if (!busy) {
            busy = true;
            createConnection();
            connection.createStatement().execute(SQL_DELETE + " where id = " + id);
            System.out.println("Record Successfully Deleted");
            closeConnection();
            busy = false;
        }
    }

    public void deleteAll() throws SQLException {
        if (!busy) {
            busy = true;
            createConnection();
            connection.createStatement().execute(SQL_DELETE + " where 1 = 1");
            System.out.println("Record Successfully Deleted");
            closeConnection();
            busy = false;
        }
    }

    private void createConnection() throws SQLException {
        if (connection == null)
            connection = DriverManager.getConnection(JDBC_URL);
        statement = connection.createStatement();
    }

    public void closeConnection() throws SQLException {
        if (statement != null)
            statement.close();
        if (connection != null)
            connection.close();
        connection = null;
    }

    public void resetCurrentPlayer() throws SQLException {
        if (!busy) {
            busy = true;
            createConnection();
            connection.createStatement().execute(SQL_UPDATE + " active = false where active = true");
            closeConnection();
            busy = false;
        }
    }

    public int getRowCount() {
        return rowCount;
    }
}