package aemet;

import java.time.LocalDateTime;

public class Weather {
    LocalDateTime ts;
    String place;
    String station;
    Double temperature;
    Double latitud;
    Double longitud;

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

    public void setLatitud(Double latitud){this.latitud = latitud;}

    public void setLongitud(Double longitud){this.longitud = longitud;}

    public LocalDateTime getTs(){
        return ts;
    }

    public String getPlace(){
        return place;
    }

    public String getStation(){
        return station;
    }

    public Double getTemperature(){
        return temperature;
    }

    public Double getLongitud(){
        return longitud;
    }
    public Double getLatitud(){
        return latitud;
    }
}
