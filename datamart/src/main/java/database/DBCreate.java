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
            statement.execute("DROP TABLE IF EXISTS maximum;");
            statement.execute("DROP TABLE IF EXISTS minimum;");
            statement.execute("CREATE TABLE IF NOT EXISTS maximum (" +
                    "value DOUBLE, " +
                    "date TEXT," +
                    "time TEXT, " +
                    "place TEXT," +
                    "station TEXT" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS minimum (" +
                    "value DOUBLE, "+
                    "date TEXT, " +
                    "time TEXT," +
                    "place TEXT," +
                    "station TEXT" +
                    ")");
        }
    }

    @Override
    public void insert() {

    }

}
