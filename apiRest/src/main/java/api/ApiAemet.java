package api;
import select.SelectMaxMin;
import spark.Spark;

import java.time.LocalDate;

import static spark.Spark.port;

public class ApiAemet implements Api{

    JsonTransformer jsonTransformer;

    public ApiAemet(SelectMaxMin selectMaxMin){
        jsonTransformer = new JsonTransformer(selectMaxMin);
        initializeRoutes();
    }

    public void initializeRoutes() {

        port(8080);

        Spark.get("/v1/places/with-max-temperature","application/json",  (req, res) -> {
            res.type("application/json");
            String dateFrom = req.queryParams("from") == null ? LocalDate.now().toString() : req.queryParams("from");
            String dateTo = req.queryParams("to") == null ? LocalDate.now().toString() : req.queryParams("to");
            return jsonTransformer.jsonTransformerMax(dateFrom, dateTo);
        });

        Spark.get("/v1/places/with-min-temperature","application/json",  (req, res) -> {
            res.type("application/json");
            String dateFrom = req.queryParams("from") == null ? LocalDate.now().toString() : req.queryParams("from");
            String dateTo = req.queryParams("to") == null ? LocalDate.now().toString() : req.queryParams("to");
            return jsonTransformer.jsonTransformerMin(dateFrom, dateTo);
        });

    }



}
