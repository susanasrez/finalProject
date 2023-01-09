package aemet;

import com.google.gson.*;

import org.jsoup.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class AemetWeatherSensor implements WeatherSensor{

    private final String apiKey;
    private static final String url = "https://opendata.aemet.es/opendata/api/observacion/convencional/todas";

    public AemetWeatherSensor(String apiKey){
        this.apiKey = apiKey;
    }

    public List<Weather> read() throws IOException {
        Gson gson = new Gson();
        String res = request(url);
        JsonObject jsonObject = gson.fromJson(res, JsonObject.class);
        String urlData = jsonObject.get("datos").getAsString();
        String content = request(urlData);
        JsonArray jsonElements = gson.fromJson(content, JsonArray.class);
        return readArea(jsonElements);
    }

    public List<Weather> readArea(JsonArray jsonElements){
        List<Weather> weatherList = new ArrayList<>();
        for (JsonElement jsonElement : jsonElements) {
            JsonObject o = (JsonObject) jsonElement;
            weatherList.add(toWeather(o));
        }
        return weatherList.stream()
                .filter(w -> w.getLatitud() > 27.5 && w.getLatitud() < 28.4)
                .filter(w -> w.getLongitud() > -16.00 && w.getLongitud() < -15.00)
                .collect(Collectors.toList());
    }

    public String request(String url) throws IOException {
        return Jsoup.connect(url)
                .ignoreContentType(true)
                .header("accept", "application/json")
                .header("api_key", apiKey)
                .method(Connection.Method.GET)
                .maxBodySize(0).execute().body();
    }

    public Weather toWeather(JsonObject o){
        Weather weather = new Weather();
        weather.setTs(LocalDateTime.parse(o.get("fint").getAsString()));
        weather.setPlace(o.get("ubi").getAsString());
        weather.setStation(o.get("idema").getAsString());
        try {
            weather.setTemperature(o.get("ta").getAsDouble());
        }catch (NullPointerException e){
            weather.setTemperature(0.0);
        }
        weather.setLatitud(o.get("lat").getAsDouble());
        weather.setLongitud(o.get("lon").getAsDouble());
        return weather;
    }


}
