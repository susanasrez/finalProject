package aemet;

import java.time.LocalDateTime;

public class Weather {
    LocalDateTime ts;
    String place;
    String station;
    Double temperature;

    public Weather(){

    }

    public void setTs(LocalDateTime ts){
        this.ts = ts;
    }

    public void setPlace(String place){
        this.place = place;
    }

    public  void setStation(String station){
        this.station = station;
    }

    public void setTemperature(Double temperature){
        this.temperature = temperature;
    }
}
