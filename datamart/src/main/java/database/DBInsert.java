package database;

import aemet.Weather;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


public class DBInsert implements DBManager{

    private final Connection conn;

    public DBInsert(Connection conn) throws SQLException {
        this.conn = conn;
        insert();
    }

    public void insert() throws SQLException {
        delete();
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

    public void delete() {
        String SqlDeleteMax = "DELETE FROM maximum WHERE date = ?";
        String SqlDeleteMin = "DELETE FROM minimum WHERE date = ?";
        try(PreparedStatement delMax = conn.prepareStatement(SqlDeleteMax)){
            delMax.setString(1, LocalDate.now().toString());
            delMax.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e);
        }
        try(PreparedStatement delMin = conn.prepareStatement(SqlDeleteMin)){
            delMin.setString(1, LocalDate.now().toString());
            delMin.executeUpdate();
        }catch (SQLException e) {
        System.out.println(e);
        }
    }


    @Override
    public void create() {

    }
}
