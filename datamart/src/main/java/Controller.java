import database.DBCreate;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Controller {

    public Controller() throws SQLException {
        Connection conn = connect();
        new DBCreate(conn);
    }

    public Connection connect() {
        String dbPath = ".\\datamart\\datamart.db";
        File dbFile = new File(dbPath);
        String absolutePath = "jdbc:sqlite:" + dbFile.getAbsolutePath();
        Connection conn;
        try {
            conn = DriverManager.getConnection(absolutePath);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

}
