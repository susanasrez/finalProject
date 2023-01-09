package database;

import java.sql.SQLException;

public interface DBManager {

    void create() throws SQLException;

    void insert() throws SQLException;

    void delete();

}
