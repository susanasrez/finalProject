package api;
import aemet.Weather;
import com.google.gson.Gson;
import select.Select;
import select.SelectMaxMin;
import spark.Spark;

import java.sql.SQLException;
import java.time.LocalDate;

import static spark.Spark.port;

public class ApiAemet implements Api{

    Select select;

    public ApiAemet(SelectMaxMin selectMaxMin){
        select = selectMaxMin;
        initializeRoutes();
    }

    public void initializeRoutes() {

        port(8080);

        Spark.get("/v1/places/with-max-temperature","application/json",  (req, res) -> {
            res.type("application/json");
            String dateFrom = req.queryParams("from") == null ? LocalDate.now().toString() : req.queryParams("from");
            String dateTo = req.queryParams("to") == null ? LocalDate.now().toString() : req.queryParams("to");
            return jsonTransformerMax(dateFrom, dateTo);
        });

        Spark.get("/v1/places/with-min-temperature","application/json",  (req, res) -> {
            res.type("application/json");
            String dateFrom = req.queryParams("from") == null ? LocalDate.now().toString() : req.queryParams("from");
            String dateTo = req.queryParams("to") == null ? LocalDate.now().toString() : req.queryParams("to");
            return jsonTransformerMin(dateFrom, dateTo);
        });

    }

    public String jsonTransformerMax(String from, String to) throws SQLException {
        Weather weatherMax = select.maximum(from, to);
        Gson gson = new Gson();
        return gson.toJson(weatherMax);
    }

    public String jsonTransformerMin(String from, String to) throws SQLException {
        Weather weatherMin = select.minimum(from, to);
        Gson gson = new Gson();
        return gson.toJson(weatherMin);
    }

}
