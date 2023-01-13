package datalakeManager;

import aemet.Weather;

import java.util.List;

public class CalculatorMaxMin{

    DatalakeManager readDataLake;

    public CalculatorMaxMin(){
        readDataLake = new ReadDataLake();
    }

    public Weather maximum(){
        List<Weather> weatherList = readDataLake.read();
        return weatherList.stream()
                .reduce((w1,w2)-> {
                    if(w1.getTemperature() > w2.getTemperature()){
                        return w1;
                    }
                    else{
                        return w2;
                    }
                }).orElse(null);
    }

    public Weather minimum(){
        List<Weather> weatherList = readDataLake.read();
        return weatherList.stream()
                .reduce((w1,w2)-> {
                    if(w1.getTemperature() < w2.getTemperature()){
                        return w1;
                    }
                    else{
                        return w2;
                    }
                }).orElse(null);
    }


}
