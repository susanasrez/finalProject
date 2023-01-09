package aemet;

import java.util.List;

public interface WeatherSensor {

    List<Weather> read();
}
