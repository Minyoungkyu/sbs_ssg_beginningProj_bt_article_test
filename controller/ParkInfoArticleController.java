package controller;

import java.util.Scanner;

import service.ParkInfoArticleService;
import util.Util;

public class ParkInfoArticleController { // 새로 추가된 Controller class
	private Scanner sc;
	private int parkId;
	private String parkName;
	private ParkInfoArticleService parkInfoArticleService; // service 패키지 ParkInfoArticleSerive 추가.

	public ParkInfoArticleController(int parkId, String parkName) {
		this.parkName = parkName;
		this.parkId = parkId;
		this.parkInfoArticleService = new ParkInfoArticleService(parkId);
		this.sc = new Scanner(System.in);
		while1: while (true) {
			showParkInfoArticleMenu();
			System.out.println();
			System.out.printf("원하는 메뉴를 입력하세요 >> ");

			String input = sc.next();
			sc.nextLine();
			int command = -1;

			if (Util.isNum(input)) {
				command = Integer.parseInt(input);
			} else {
				System.out.println("잘못된 입력입니다. 메뉴에 해당하는 숫자를 입력하세요.\n");
				System.out.println();

				continue;
			}

			if ((command > 5 && command != 9) || command < 0) {
				System.out.println("없는 메뉴 입니다. 메뉴번호를 다시 입력하세요.\n");
				System.out.println();
				continue;
			} else if (command == 0) {
				new MainMenuController();
				break;
			} else if (command == 9) {
				new ParkInfoController();
				break;
			} else {
				if (command == 1) {
					System.out.println();
					System.out.println("<게시글 검색>");
					System.out.println();
					System.out.printf("검색 내용 입력 >> ");
					String searchKey = sc.nextLine();
					parkInfoArticleService.showSearchedArticle(searchKey);// 게시글 검색
				}
				if (command == 2) {
					System.out.println();
					System.out.println("<게시글 열기>");
					System.out.println();
					System.out.printf("게시글 제목 입력 >> ");
					String articleTitle = sc.nextLine();
					if (parkInfoArticleService.showArticleDetail(articleTitle, sc) == 0) {
						showArticleDetailMenu();
						int command2 = sc.nextInt();
						sc.nextLine();
						if (command2 == 1) {
							parkInfoArticleService.doArticleRecommendIncrease(articleTitle);

						} else if (command2 == 2) {
							parkInfoArticleService.doArticleRecommendDecrease(articleTitle);

						} else if (command2 == 3) {
							parkInfoArticleService.showArticleRecommendList(articleTitle);

						} else if (command2 == 4) {
							System.out.println();
							System.out.println("< 댓글 쓰기 >");
							System.out.println();
							System.out.print("댓글 입력 >> ");
							String body = sc.nextLine();
							parkInfoArticleService.doArticleReplyWrite(articleTitle, body);
						} else
							break;
					}

				}
				if (command == 3) {
					System.out.println();
					System.out.println("<게시글 작성>");
					System.out.printf("제목입력 >> ");
					String title = sc.nextLine();
					System.out.printf("내용입력 >> ");
					String body = sc.nextLine();
					parkInfoArticleService.doArticleWrite(title, body, parkId); // 게시글 작성 (로그인 옵션 추가 필요)

				}
				if (command == 4) {
					System.out.println();
					System.out.println("<게시글 수정>");
					System.out.println();
					System.out.printf("게시글 제목 입력 >> ");
					String articleTitle = sc.nextLine();
					System.out.println();
					parkInfoArticleService.doArticleModify(articleTitle, sc); // 게시글 수정 ( 로그인 옵션 추가 필요 // memberId 와 대조
																				// 권한 확인)
				}
				if (command == 5) {
					System.out.println();
					System.out.println("<게시글 삭제>");
					System.out.println();
					System.out.printf("게시글 제목 입력 >> ");
					String articleTitle = sc.nextLine();
					System.out.println();
					parkInfoArticleService.doArticleDelete(articleTitle, sc); // 게시글 삭제 ( 로그인 옵션 추가 필요 // memberId 와 대조
																				// 권한 확)
				}

			} // else
		} // while 끝
	} // constructor 끝

	private void showParkInfoArticleMenu() {
		System.out.println("\n");
		System.out.println("< " + parkName + " 자유게시판 >");
		System.out.println();
		System.out.println("--------------------------");
		parkInfoArticleService.showArticleList(); // 게시글 목록
		System.out.println("--------------------------");
		System.out.println();
		System.out.println("1. 게시글 검색");
		System.out.println("2. 게시글 조회");
		System.out.println("3. 게시글 작성");
		System.out.println("4. 게시글 수정");
		System.out.println("5. 게시글 삭제");
		System.out.println("9. 구단선택으로 돌아가기");
		System.out.println("0. 메인으로 돌아가기\n");
	}

	private void showArticleDetailMenu() {
		System.out.println("1. 추천하기"); // 추천기능 너무 막 만들었다. 빼버리던가 업그레이드 하는것이 좋겠다.
		System.out.println("2. 추천취소");
		System.out.println("3. 댓글보기");
		System.out.println("4. 댓글작성");
		System.out.println("5. 뒤로가기");
	}

}
