package aemet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.List;

public interface WeatherSensor {

    List<Weather> read() throws IOException;
    List<Weather> readArea(JsonArray jsonElements);
    String request(String url) throws IOException;
    Weather toWeather(JsonObject o);
}
