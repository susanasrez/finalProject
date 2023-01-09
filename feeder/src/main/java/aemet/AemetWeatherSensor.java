package aemet;

import com.google.gson.*;

import org.jsoup.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class AemetWeatherSensor implements WeatherSensor{

    private final String apiKey;
    private static final String url = "https://opendata.aemet.es/opendata/api/observacion/convencional/todas";

    public AemetWeatherSensor(String apiKey){
        this.apiKey = apiKey;
    }

    public List<Weather> read() {
        Gson gson = new Gson();
        try{
            String res = request(url);
            JsonObject jsonObject = gson.fromJson(res, JsonObject.class);
            String urlData = jsonObject.get("datos").getAsString();
            String content = request(urlData);
            JsonArray jsonElements = gson.fromJson(content, JsonArray.class);
            List<Weather> weatherList = new ArrayList<>();
            for (JsonElement jsonElement : jsonElements) {
                JsonObject o = (JsonObject) jsonElement;
                if (-16.00 < o.get("lon").getAsDouble() && o.get("lon").getAsDouble() < -15.00 &&
                        27.5 < o.get("lat").getAsDouble() && o.get("lat").getAsDouble() < 28.4) {
                    weatherList.add(toWeather(o));
                }
            }
            return weatherList;
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private String request(String url) throws IOException {
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
        weather.setTemperature(o.get("ta").getAsDouble());
        return weather;
    }


}
