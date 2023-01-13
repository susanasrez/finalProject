package api;

import aemet.Weather;
import com.google.gson.Gson;
import select.Select;
import select.SelectMaxMin;

import java.sql.SQLException;

public class JsonTransformer {
    Select select;

    public JsonTransformer(SelectMaxMin selectMaxMin){
        select = selectMaxMin;
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
