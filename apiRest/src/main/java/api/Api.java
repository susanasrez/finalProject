package api;

import java.sql.SQLException;

public interface Api {
    void initializeRoutes();
    String jsonTransformerMax(String from, String to) throws SQLException;
    String jsonTransformerMin(String from, String to) throws SQLException;
}
