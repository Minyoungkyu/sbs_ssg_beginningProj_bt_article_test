package dao;

import java.sql.*;

public class ParkInfoDao {
    private String url = "jdbc:mysql://localhost:3306/baseball_tonight";
    private String userName = "root";
    private String password = "";
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private String setNull = "등록된 정보가 없습니다\n";
    public ParkInfoDao(int parkId) throws SQLException {
        connection = DriverManager.getConnection(url, userName, password);
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select * from park_info where id = " + parkId);
        resultSet.next();
    }

    public String getParkName() {
        String name = "";
        try {
            name = resultSet.getString("name");
        } catch (SQLException e) {}

        return name;
    }

    public String getParkAddress() {
        String address = "";
        try {
            address = resultSet.getString("address");
        } catch (SQLException e) {}
        return address;
    }

    public String getParkFood() {
        String food = "";
        try {
            food = resultSet.getString("food");
        } catch (SQLException e) {}
        return food;
    }

    public String getParkBus() {
        String bus = "";
        try {
            bus = resultSet.getString("traffic_bus");
        } catch (SQLException e) {}

        return bus;

    }

    public String getParkBusstop() {
        String busstop = "";

        try {
            busstop = resultSet.getString("traffic_busstop");
        } catch (SQLException e) {}

        return busstop;

    }

    public String getParkSub() {

        String sub = "";
        try {
            sub = resultSet.getString("traffic_sub");
        } catch (SQLException e) {}

        if(sub.equals("")) {
            return setNull;
        }
        return sub;
    }

    public String getParkParking() {

        String parking = "";
        try {
            parking = resultSet.getString("traffic_parking");
        } catch (SQLException e) {}

        if(parking.equals("")) {
            return setNull;
        }
        return parking;
    }



}
