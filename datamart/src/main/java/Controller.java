import database.DBCreate;
import database.DBInsert;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    Connection conn;

    public Controller() throws SQLException {
        this.conn = connect();
        new DBCreate(conn);
        start();
    }

    public Connection connect() {
        String dbPath = ".\\datamart\\datamart.db";
        File dbFile = new File(dbPath);
        String absolutePath = "jdbc:sqlite:" + dbFile.getAbsolutePath();
        try {
            conn = DriverManager.getConnection(absolutePath);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public void start(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    new DBInsert(conn);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000*60, 1000 * 60 * 60);
    }

}
