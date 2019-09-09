package com.swingy.database;

import com.swingy.battle.FighterMetrics;
import com.swingy.game.entities.Fighter;
import com.swingy.id.ID;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.concurrent.Callable;

public class SwingyDB implements Callable {

    private String action = "CREATE";
    private Fighter fighter;

    private static final String DB_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL = "jdbc:derby:swingydb;create=true";
    private static final String SQL_SELECT = "select * from players";
    private static final String SQL_INSERT = "insert into players (name, xp, character_class, active) values";
    private static final String SQL_DELETE = "delete from players";
    private static final String SQL_CREATE = "create table players(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), name varchar(255), xp integer, character_class varchar (255), active boolean)";
    private static final String SQL_DROP = "drop table players";
    private static final String SQL_UPDATE = "update players set";
    private Statement statement;
    private Connection connection = null;
    private int rowCount;

    private boolean busy = false;

    public static SwingyDB swingyDB;

    public void setAction(String action) {
        this.action = action;
    }

    public void setFighter(Fighter fighter) {
        this.fighter = fighter;
    }

    public Fighter getFighter() {
        return this.fighter;
    }


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
            } else {
                if(connection == null)
                    createConnection();
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
        long id = -1;
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
                id = resultSet.getInt(1);
            }
            closeConnection();
            busy = false;
        }
        this.fighter.getFighterMetrics().setID(id);
        return id;
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

    public Fighter queryPlayer(long id) throws SQLException {
        ResultSet resultSet = null;
        Fighter player = null;
        if (!busy) {
            busy = true;
            createConnection();
            resultSet = statement.executeQuery(SQL_SELECT + " where id = " + id);
            busy = false;
        }
        if (resultSet != null) {
            if (resultSet.next()) {
                switch (resultSet.getString(4)) {
                    case "ninja":
                        player = new Fighter(new FighterMetrics(resultSet.getString(2), "NINJA"),
                                null, null);
                        player.setPlayerClass(ID.NINJA);
                        break;

                    case "dino":
                        player = new Fighter(new FighterMetrics(resultSet.getString(2), "DINO"),
                                null, null);
                        player.setPlayerClass(ID.DINO);
                        break;

                    case "robo":
                        player = new Fighter(new FighterMetrics(resultSet.getString(2), "ROBO"),
                                null, null);
                        player.setPlayerClass(ID.ROBO);
                        break;

                    case "zombo":
                        player = new Fighter(new FighterMetrics(resultSet.getString(2), "ZOMBO"),
                                null, null);
                        player.setPlayerClass(ID.ZOMBO);
                        break;
                }
                player.getFighterMetrics().setID(resultSet.getInt(1));
                player.setPlayerClassName(resultSet.getString(4));
                player.getFighterMetrics().getLevel().setExperience(resultSet.getInt(3));
            }
        }
        return player;
    }

    public Fighter queryPlayer() throws SQLException {
        ResultSet resultSet = null;
        Fighter player = null;
        if (!busy) {
            busy = true;
            createConnection();
            resultSet = statement.executeQuery(SQL_SELECT + " where active = true");
            busy = false;
        }

        if (resultSet != null) {
            if (resultSet.next()) {
                switch (resultSet.getString(4)) {
                    case "ninja":
                        player = new Fighter(new FighterMetrics(resultSet.getString(2), "NINJA"),
                                null, null);
                        player.setPlayerClass(ID.NINJA);
                        break;

                    case "dino":
                        player = new Fighter(new FighterMetrics(resultSet.getString(2), "DINO"),
                                null, null);
                        player.setPlayerClass(ID.DINO);
                        break;

                    case "robo":
                        player = new Fighter(new FighterMetrics(resultSet.getString(2), "ROBO"),
                                null, null);
                        player.setPlayerClass(ID.ROBO);
                        break;

                    case "zombo":
                        player = new Fighter(new FighterMetrics(resultSet.getString(2), "ZOMBO"),
                                null, null);
                        player.setPlayerClass(ID.ZOMBO);
                        break;
                }
                player.getFighterMetrics().setID(resultSet.getInt(1));
                player.setPlayerClassName(resultSet.getString(4));
                player.getFighterMetrics().getLevel().setExperience(resultSet.getInt(3));
            }
        }
        return player;
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
            System.out.println("Active players reset");
            closeConnection();
            busy = false;
        }
    }

    public int getRowCount() {
        return rowCount;
    }

    @Override
    public Object call() throws Exception {
        switch (action){
            case "CREATE":
                createDB();
                break;
            case "DROP":
                dropDB();
                break;
            case "INSERT":
                if (fighter != null)
                    return insertPlayer(fighter);
                break;
            case "DELETE":
                if (this.fighter != null)
                    deletePlayer(fighter.getFighterMetrics().getID());
                break;
            case "FETCH":
                return queryPlayer();
            case "ALL":
                return queryAll();
            case "RESET":
                resetCurrentPlayer();
                break;
            case "SET":
                if (this.fighter != null)
                    setCurrentPlayer(fighter.getFighterMetrics().getID());
                break;
        }
        return null;
    }
}