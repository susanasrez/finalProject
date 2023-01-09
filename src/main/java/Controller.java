import aemet.AemetWeatherSensor;
import aemet.Weather;
import aemet.WeatherSensor;
import datalake.Datalake;
import datalake.FileDatalake;

import java.io.File;
import java.util.List;
import java.util.TimerTask;
public class Controller extends TimerTask{

    private final File root;

    public Controller(String apiToken, String root){
        this.root = new File("//" + root + "//datalake");
        directory();
        WeatherSensor weatherSensor = new AemetWeatherSensor(apiToken);
        List<Weather> weatherList = ((AemetWeatherSensor) weatherSensor).read();
        FileDatalake fileDatalake = new FileDatalake(this.root);
        fileDatalake.save(weatherList);

        //private aemet.WeatherSensor sensor;
        //private final File root;
        //root = rootDatalake;
    }

    /*public void start(){
        Timer timer = new Timer();
        //HourlyTask task = new HourlyTask();
        //timer.scheduleAtFixedRate(task, 0, 3600000);

    }*/

    @Override
    public void run() {
        //sensor = new aemet.AemetWeatherSensor(apiToken);
        //datalake = new FileDatalake(rootDatalake);
    }

    public void directory(){
        if (!root.exists()) {
            root.mkdirs();
            System.out.println("Directory created successfully");
        }
    }
}
