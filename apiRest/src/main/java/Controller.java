import api.ApiAemet;
import select.Select;
import select.SelectMaxMin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Controller {

    Connection conn;
    Select select;
    public Controller(){
        this.conn = connect();
        select = new SelectMaxMin(conn);
        new ApiAemet((SelectMaxMin) select);
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


}
