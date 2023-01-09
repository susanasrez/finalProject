import select.Select;
import select.SelectMax;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Controller {

    Connection conn;
    public Controller() throws SQLException {
        this.conn = connect();
        Select select = new SelectMax(conn);
        ((SelectMax) select).maximum("2023-01-02", "2023-01-05");
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
