import aemet.*;
import datalake.FileDatalake;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Controller extends TimerTask{
    private final File root;
    private final WeatherSensor weatherSensor;

    public Controller(String apiToken){
        this.root = new File(".\\feeder\\datalakedir");
        this.weatherSensor = new AemetWeatherSensor(apiToken);
        start();
    }

    public void start(){
        if (!root.exists()) {
            root.mkdirs();
            System.out.println("Directory created successfully");
        }
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                List<Weather> weatherList;
                try {
                    weatherList = weatherSensor.read();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                FileDatalake fileDatalake = new FileDatalake(root);
                fileDatalake.save(weatherList);
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000 * 60 * 60); // 1000 milliseconds * 60 seconds * 60 minutes
    }

    @Override
    public void run() {
    }
}
