package datalakeManager;

import aemet.Weather;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadDataLake implements DatalakeManager{

    public ReadDataLake(){
    }

    public List<Weather> read(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMDD");
        File file = new File(".\\feeder\\datalakedir\\" + formatter.format(LocalDate.now()) + ".events");
        try {
            Scanner input = new Scanner(file);
            List<Weather> weatherList = new ArrayList<>();

            while (input.hasNextLine()) {
                String line = input.nextLine();
                Gson gson = new Gson();
                JsonElement element = gson.fromJson (line, JsonElement.class);
                for (JsonElement jsonElement : element.getAsJsonArray()) {
                    JsonObject o = (JsonObject) jsonElement;
                    weatherList.add(toWeather(o));
                }
            }
            input.close();
            return weatherList;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Weather toWeather(JsonObject o){
        Weather weather = new Weather();
        weather.setTs(toLocalDateTime(o));
        weather.setPlace(o.get("place").getAsString());
        weather.setStation(o.get("station").getAsString());
        weather.setTemperature(o.get("temperature").getAsDouble());
        return weather;
    }

    public LocalDateTime toLocalDateTime(JsonObject o){
        JsonObject ts = o.get("ts").getAsJsonObject();
        JsonObject date = ts.get("date").getAsJsonObject();
        int year = date.get("year").getAsInt();
        int month = date.get("month").getAsInt();
        int day = date.get("day").getAsInt();
        JsonObject time = ts.get("time").getAsJsonObject();
        int hour = time.get("hour").getAsInt();
        int minute = time.get("minute").getAsInt();
        int second = time.get("second").getAsInt();
        int nano = time.get("nano").getAsInt();
        return  LocalDateTime.of(year, month, day, hour, minute, second, nano);

    }



}
