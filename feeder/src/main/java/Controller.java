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
        directory();
        start();
    }

    public void start(){
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
        task.run();
        timer.scheduleAtFixedRate(task, 0, 1000 * 60 * 60); // 1000 milliseconds * 60 seconds * 60 minutes

    }


    public void directory(){
        if (!root.exists()) {
            root.mkdirs();
            System.out.println("Directory created successfully");
        }
    }

    @Override
    public void run() {

    }
}
