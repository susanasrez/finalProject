package select;

import aemet.Weather;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SelectMax implements Select{

    Connection conn;

    public SelectMax(Connection conn){
        this.conn = conn;
    }

    public void maximum(String from, String to) throws SQLException {
       List<LocalDate> days = days(from,to);
       List<Weather> weatherList = select(days);
        for (Weather weather : weatherList) {
            System.out.println(weather.toString());
        }
        //obtener el máximo de los weather
    }

    public List<LocalDate> days(String from, String to){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(from, formatter);
        LocalDate end = LocalDate.parse(to, formatter);

        List<LocalDate> localDates = new ArrayList<>();
        for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
            localDates.add(date);
        }
        return localDates;
    }

    public List<Weather> select(List<LocalDate> days) throws SQLException {
        List<Weather> weatherList = new ArrayList<>();
        String sql = "SELECT * FROM maximum WHERE date = ?";
        for (LocalDate day : days) {
            String date = day.toString();
            Weather weather = new Weather();
            PreparedStatement s = conn.prepareStatement(sql);
            s.setString(1, date);
            ResultSet rs = s.executeQuery();
            while(rs.next()) {
                weather.setTemperature(rs.getDouble("value"));
                weather.setPlace(rs.getString("place"));
                weather.setStation(rs.getString("station"));
                weather.setTs(toLocalDateTime(rs));
            }
            weatherList.add(weather);
        }
        return weatherList;
    }

    public LocalDateTime toLocalDateTime(ResultSet rs) throws SQLException {
        LocalDate localDate = LocalDate.parse(rs.getString("date"));
        String timeString = rs.getString("time") +":00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime time = LocalTime.parse(timeString, formatter);
        return localDate.atTime(time);
    }


}
