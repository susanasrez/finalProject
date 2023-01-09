package database;

import aemet.Weather;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class DBInsert implements DBManager{

    private final Connection conn;

    public DBInsert(Connection conn){
        this.conn = conn;
        insert();
    }

    public void insert(){
        String sqlMax = "INSERT INTO maximum(date,time,place,station,value) VALUES(?,?,?,?,?)";
        ReadDataLake readDataLake = new ReadDataLake();
        Weather max = readDataLake.maximum();
        try (PreparedStatement pstm = conn.prepareStatement(sqlMax)){
            pstm.setString(1, max.getTs().toLocalDate().toString());
            pstm.setString(2, max.getTs().toLocalTime().toString());
            pstm.setString(3, max.getPlace());
            pstm.setString(4, max.getStation());
            pstm.setDouble(5, max.getTemperature());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String sqlMin = "INSERT INTO minimum(date,time,place,station,value) VALUES(?,?,?,?,?)";
        Weather min = readDataLake.minimum();
        try (PreparedStatement pstm = conn.prepareStatement(sqlMin)){
            pstm.setString(1, min.getTs().toLocalDate().toString());
            pstm.setString(2, min.getTs().toLocalTime().toString());
            pstm.setString(3, min.getPlace());
            pstm.setString(4, min.getStation());
            pstm.setDouble(5, min.getTemperature());
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void create() {

    }
}
