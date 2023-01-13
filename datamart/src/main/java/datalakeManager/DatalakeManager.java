package datalakeManager;

import aemet.Weather;
import com.google.gson.JsonObject;

import java.time.LocalDateTime;
import java.util.List;

public interface DatalakeManager {

    List<Weather> read();
    Weather toWeather(JsonObject o);
    LocalDateTime toLocalDateTime(JsonObject o);

}
