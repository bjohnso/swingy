package com.swingy.database;

import com.swingy.rendering.entities.Fighter;

import javax.management.Query;
import javax.swing.*;
import java.sql.*;

public class SwingyDB {

    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL = "jdbc:derby:swingydb;create=true";
    private static final String SQL_SELECT = "select * from players";
    private static final String SQL_INSERT = "insert into players (name, xp, character_class, active) values";
    private static final String SQL_DELETE = "delete from players";
    private static final String SQL_CREATE = "create table players(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), name varchar(255), xp integer, character_class varchar (255), active boolean)";
    private static final String SQL_DROP = "drop table players";
    private Statement statement;

    private Connection connection;

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
        if(res.next()) {
            closeConnection();
            return true;
        }
        else{
            closeConnection();
            return false;
        }
    }

    public void createDB() {
        try {
            if (existDB()) {
                System.out.println("SwingDB Already Exists");
                return;
            }
            else {
                createConnection();
                connection.createStatement().execute(SQL_CREATE);
                System.out.println("SwingDB Successfully Created");
                closeConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropDB() throws SQLException {
        createConnection();
        connection.createStatement().execute(SQL_DROP);
        closeConnection();
    }

    public long insertPlayer(Fighter fighter) throws SQLException {
        createConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT
                + " ('" + fighter.getFighterMetrics().getName() + "'"
                + "," + (int)fighter.getFighterMetrics().getLevel().getExperience()
                + ",'" + fighter.getPlayerClassName() + "'"
                + "," + true + ")", PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.execute();

        //Get Generated ID
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()){
            System.out.println("Record Successfully Inserted with ID: " + resultSet.getInt(1));
            return resultSet.getInt(1);
        }
        else
            return -1;
    }

    public ResultSet queryPlayer(long id) throws SQLException {
        createConnection();
        return statement.executeQuery(SQL_SELECT + " where id = " + id);
    }

    public ResultSet queryAll() throws SQLException {
        createConnection();
       return statement.executeQuery(SQL_SELECT);
    }

    public void deletePlayer(long id) throws SQLException {
        createConnection();
        connection.createStatement().execute(SQL_DELETE + " where id = " + id);
        System.out.println("Record Successfully Deleted");
        closeConnection();
    }

    private void createConnection() throws SQLException {
        connection = DriverManager.getConnection(JDBC_URL);
        statement = connection.createStatement();
    }

    private void closeConnection() throws SQLException {
        if (statement != null)
            statement.close();
        if (connection != null)
            connection.close();
    }
}