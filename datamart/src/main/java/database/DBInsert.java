package database;

import aemet.Weather;
import datalakeManager.CalculatorMaxMin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class DBInsert implements DBManager{

    private final Connection conn;
    CalculatorMaxMin calculatorMaxMin;

    public DBInsert(Connection conn, CalculatorMaxMin calculatorMaxMin) throws SQLException {
        this.calculatorMaxMin = calculatorMaxMin;
        this.conn = conn;
        insert();
    }

    public void insert() {
        delete();
        Weather max = calculatorMaxMin.maximum();
        String sqlMax = "INSERT INTO maximum(date,time,place,station,value) VALUES(?,?,?,?,?)";
        try (PreparedStatement pstm = conn.prepareStatement(sqlMax)){
            pstm.setString(1, max.getTs().toLocalDate().toString());
            pstm.setString(2, max.getTs().toLocalTime().toString());
            pstm.setString(3, max.getPlace());
            pstm.setString(4, max.getStation());
            pstm.setDouble(5, max.getTemperature());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String sqlMin = "INSERT INTO minimum(date,time,place,station,value) VALUES(?,?,?,?,?)";
        Weather min = calculatorMaxMin.minimum();
        try (PreparedStatement pstm = conn.prepareStatement(sqlMin)){
            pstm.setString(1, min.getTs().toLocalDate().toString());
            pstm.setString(2, min.getTs().toLocalTime().toString());
            pstm.setString(3, min.getPlace());
            pstm.setString(4, min.getStation());
            pstm.setDouble(5, min.getTemperature());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete() {
        String SqlDeleteMax = "DELETE FROM maximum WHERE date = ?";
        String SqlDeleteMin = "DELETE FROM minimum WHERE date = ?";
        try(PreparedStatement delMax = conn.prepareStatement(SqlDeleteMax)){
            delMax.setString(1, LocalDate.now().toString());
            delMax.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try(PreparedStatement delMin = conn.prepareStatement(SqlDeleteMin)){
            delMin.setString(1, LocalDate.now().toString());
            delMin.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create() {
    }
}
