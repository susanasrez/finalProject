package database;

import aemet.Weather;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class prueba {

    private final Connection conn;

    public prueba(Connection conn) throws SQLException {
        this.conn = conn;
        insert();
    }

    public void insert() throws SQLException {
        String sqlMax = "INSERT INTO maximum(date,time,place,station,value) VALUES(?,?,?,?,?)";
        ReadDataLake readDataLake = new ReadDataLake();
        Weather max = readDataLake.maximum();
        try (PreparedStatement pstm = conn.prepareStatement(sqlMax)){
            pstm.setString(1, "2023-01-01");
            pstm.setString(2, "12:00");
            pstm.setString(3, "Gáldar");
            pstm.setString(4, "GG");
            pstm.setDouble(5, 28.5);
            pstm.executeUpdate();
            pstm.setString(1, "2023-01-02");
            pstm.setString(2, "13:00");
            pstm.setString(3, "guía");
            pstm.setString(4, "Gu");
            pstm.setDouble(5, 29.5);
            pstm.executeUpdate();
            pstm.setString(1, "2023-01-03");
            pstm.setString(2, "15:00");
            pstm.setString(3, "agaete");
            pstm.setString(4, "ag");
            pstm.setDouble(5, 30.5);
            pstm.executeUpdate();
            pstm.setString(1, "2023-01-04");
            pstm.setString(2, "12:00");
            pstm.setString(3, "lp");
            pstm.setString(4, "LP");
            pstm.setDouble(5, 28.7);
            pstm.executeUpdate();
            pstm.setString(1, "2023-01-05");
            pstm.setString(2, "13:00");
            pstm.setString(3, "ff");
            pstm.setString(4, "ff");
            pstm.setDouble(5, 29.7);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }



}
