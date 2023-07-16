package service;

import dao.ParkInfoDao;

import java.sql.SQLException;

public class ParkInfoService {
    private int parkId;
    private ParkInfoDao parkInfoDao;
    private String setNull = "등록된 정보가 없습니다.";
    public ParkInfoService(int parkId) {
        this.parkId = parkId;
        try {
            parkInfoDao = new ParkInfoDao(parkId);
        } catch (SQLException e ) {}
    }

    public void showParkName() {
        String name = parkInfoDao.getParkName();
        System.out.println();
        System.out.println("< 경기장 이름 >");
        System.out.println(name);
        System.out.println();

    }

    public void showParkAddress() {
        String address = parkInfoDao.getParkAddress();
        System.out.println();
        System.out.println("< 경기장 주소 >");
        System.out.println(address);
        System.out.println();
    }

    public void showParkFood() {
        String food = parkInfoDao.getParkFood();
        System.out.println();
        System.out.println("< 식음료 정보 >");
        System.out.println(food);
        System.out.println();
    }

    public void showParkBus() {
        String busstop = parkInfoDao.getParkBusstop();
        String bus = parkInfoDao.getParkBus();

        System.out.println();
        System.out.println("< 경기장 찾기 - 버스 >");
        System.out.println("<버스정류장>");
        System.out.println(busstop);
        System.out.println();
        System.out.println("<버스>");
        System.out.println(bus);
        System.out.println();

    }

    public void showParkSub() {
        System.out.println();
        System.out.println("< 경기장 찾기 - 지하철 >");

        String sub = parkInfoDao.getParkSub();

        System.out.println(sub);
        System.out.println();
    }

    public void showParkParking() {
        System.out.println();
        System.out.println("< 주차장 >");

        String parking = parkInfoDao.getParkParking();

        System.out.println(parking);
        System.out.println();
    }




}
