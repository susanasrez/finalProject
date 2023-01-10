package select;

import aemet.Weather;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface Select {

    Weather maximum(String from, String to) throws SQLException;

    Weather minimum(String from, String to) throws SQLException;
    List<LocalDate> days(String from, String to);
    List<Weather> select(List<LocalDate> days) throws SQLException;

    LocalDateTime toLocalDateTime(ResultSet rs) throws SQLException;

}
