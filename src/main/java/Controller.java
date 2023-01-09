import java.util.List;
import java.util.TimerTask;
public class Controller extends TimerTask{

    //Private final Datalake datalake;

    public Controller(String apiToken){
        //private WeatherSensor sensor;
        //private final File root;
        //root = rootDatalake;
        AemetWeatherSensor sensor1 = new AemetWeatherSensor(apiToken);
        List<Weather> weatherList = sensor1.read();
        System.out.println(weatherList);
    }

    /*public void start(){
        Timer timer = new Timer();
        //HourlyTask task = new HourlyTask();
        //timer.scheduleAtFixedRate(task, 0, 3600000);

    }*/

    @Override
    public void run() {
        //sensor = new AemetWeatherSensor(apiToken);
        //datalake = new FileDatalake(rootDatalake);
    }
}
