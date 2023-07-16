package controller;

import service.ParkInfoService;
import util.Util;

import java.util.Scanner;

public class ParkInfoController {
    private Scanner sc;
    private int parkId;
    private ParkInfoService parkInfoService;
    private String[] parkNames = {"롯데 자이언츠", "한화이글스", "KIA 타이거즈", "LG 트윈스", "두산 베어스", "NC 다이노스", "SSG 랜더스", "KT 위즈", "키움 히어로즈", "삼성 라이온즈"};

    public ParkInfoController() {
        sc = new Scanner(System.in);
        ParkInfoController_selectPark();
        } // while 끝



    private void ParkInfoController_selectPark() {

        while1 : while (true) {
            showParkMenu();

            System.out.printf("원하는 구단을 입력하세요 >> ");

            String input = sc.next();
            sc.nextLine();
            int command = -1;

            if (Util.isNum(input)) {
                command = Integer.parseInt(input);
            } else {
                System.out.println("잘못된 입력입니다. 구단에 해당하는 숫자를 입력하세요.\n");
                System.out.println();

                continue;
            }

            if (command > 9 || command < 0) {
                System.out.println("없는 메뉴 입니다. 구단번호를 다시 입력하세요.\n");
                System.out.println();
                continue;
            } else {
                parkId = command;
                ParkInfoController_selectParkInfo();
                break;

            }
        }
    }

    private void ParkInfoController_selectParkInfo() {
        while1 : while (true) {
            showParkInfoMenu();

            System.out.printf("원하는 메뉴를 입력하세요 >> ");

            String input = sc.next();
            sc.nextLine();
            int command = -1;

            if (Util.isNum(input)) {
                command = Integer.parseInt(input);
            } else {
                System.out.println("잘못된 입력입니다. 구단에 해당하는 숫자를 입력하세요.\n");
                System.out.println();

                continue;
            }

            if ((command > 6 && command != 9) || command < 0) {
                System.out.println("없는 메뉴 입니다. 메뉴번호를 다시 입력하세요.\n");
                System.out.println();
                continue;
            } else if (command == 0) {
                new MainMenuController();
                break;
            } else if (command == 9) {
                ParkInfoController_selectPark();
                break;
            }else {
                parkInfoService = new ParkInfoService(parkId);

                if (command == 1) parkInfoService.showParkName();
                if (command == 2) parkInfoService.showParkAddress();
                if (command == 3) parkInfoService.showParkFood();
                if (command == 4) parkInfoService.showParkBus();
                if (command == 5) parkInfoService.showParkSub();
                if (command == 6) parkInfoService.showParkParking();

            } // else
        }
    }



    private void showParkInfoMenu() {
        System.out.println("\n");
        System.out.println("< " + parkNames[parkId] + " >");
        System.out.println("1. 경기장 이름");
        System.out.println("2. 주소");
        System.out.println("3. 식음료 정보");
        System.out.println("4. 경기장 찾기 - 버스");
        System.out.println("5. 경기장 찾기 - 지하철");
        System.out.println("6. 주차장");
        System.out.println("9. 다른구단 선택");
        System.out.println("0. 메인으로 돌아가기\n");
    }

    private void showParkMenu() {
        System.out.println("\n");
        System.out.println("<구단 선택>");
        System.out.println("1. 한화이글스");
        System.out.println("2. KIA 타이거즈");
        System.out.println("3. LG 트윈스");
        System.out.println("4. 두산 베어스");
        System.out.println("5. NC 다이노스");
        System.out.println("6. SSG 랜더스");
        System.out.println("7. KT 위즈");
        System.out.println("8. 키움 히어로즈");
        System.out.println("9. 삼성 라이온즈");
        System.out.println("0. 롯데 자이언츠\n");
    }




}
