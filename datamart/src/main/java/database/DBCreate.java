package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCreate implements DBManager{

    private final Connection conn;

    public DBCreate(Connection conn) throws SQLException {
        this.conn = conn;
        create();
    }

    public void create() throws SQLException {
        try (Statement statement = conn.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS maximum (" +
                    "date TEXT PRIMARY KEY," +
                    "value DOUBLE, " +
                    "time TEXT, " +
                    "place TEXT," +
                    "station TEXT" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS minimum (" +
                    "date TEXT PRIMARY KEY, " +
                    "value DOUBLE, "+
                    "time TEXT," +
                    "place TEXT," +
                    "station TEXT" +
                    ")");
        }
    }

    @Override
    public void insert() {

    }

    @Override
    public void delete() {

    }

}
