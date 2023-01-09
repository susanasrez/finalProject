package datalake;

import aemet.Weather;

import java.util.List;

public interface Datalake {

    void save(List<Weather> weatherList);

}
