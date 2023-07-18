package service;

import java.text.SimpleDateFormat;
import java.util.*;

import dao.ParkInfoArticleDao;
import dto.Article;

public class ParkInfoArticleService {
	
	private ParkInfoArticleDao parkInfoArticleDao;
	private ArrayList<Article> articles;
	
	public ParkInfoArticleService(int parkId) {
		this.parkInfoArticleDao = new ParkInfoArticleDao(parkId);
	}
	
	public void showArticleList() {
		articles = parkInfoArticleDao.getArticleList();
		
		System.out.println();
		System.out.println("<게시글 목록>");
		System.out.println();
		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다.");
			System.out.println();
		}
		for (Article a : articles) {
			System.out.println(a.id + "번 게시글 " + "( 제목 : " + a.title + " )" + " ( 조회수 : " + a.hit + " ) " + " ( 추천수 : " + a.recommend + " ) ");
		}
		System.out.println();
	}
	
	public void showSearchedArticle(String searchKey) {
		articles = parkInfoArticleDao.getSearchedArticle(searchKey);
		System.out.println();
		System.out.println("< 검색 결과 >\n");
		
		if(articles.size() == 0) {
			System.out.println("검색된 게시글이 없습니다.");
		}
		for (Article a : articles) {
			System.out.println(a.id + "번 게시글 " + "( 제목 : " + a.title + " )" + " ( 조회수 : " + a.hit + " ) " + " ( 작성일 : " + a.regDate + " ) ");
		}
		
		
	}
	
	public void showArticleDetail(String articleTitle,Scanner sc)	{
		Article article = parkInfoArticleDao.getArticle(articleTitle);
		
		if(article == null) {
			System.out.println();
			System.out.println("제목 입력이 잘못되었습니다.");
			System.out.println("정확한 제목을 입력해주세요.");
			System.out.println();
			return;
		}
		
		System.out.println(article.id + "번 게시글 ( 조회수 : " + article.hit + " ) "
				+ " (작성시간 : " + article.regDate + " )");
		System.out.println("작성자 : " + article.memberId); 
		System.out.println("제목 : " + article.title);
		System.out.println("내용 : " + article.body);
		System.out.println("추천수 : " + article.recommend);
		System.out.println();
		System.out.println();

		System.out.println("1. 추천하기"); // 추천기능 너무 막 만들었다. 빼버리던가 업그레이드 하는것이 좋겠다.
		System.out.println("2. 추천취소");
		System.out.println("3. 뒤로가기");
		int command = sc.nextInt();
		if ( command == 1 ) {
			parkInfoArticleDao.increaseRecommend(article.id);
			return;
		} else if ( command == 2 ) {
			parkInfoArticleDao.decreaseRecommend(article.id);
			return;
		} else return;

		
		
	}
	
	public void doArticleWrite(String title, String body, int parkId) { // 로그인 옵션 필요
		parkInfoArticleDao.doArticleWrite(title,body,parkId);

		System.out.println("게시글이 작성되었습니다.");
		System.out.println();
		
	}
	
	public void doArticleModify(String articleTitle, Scanner sc) { // 로그인 옵션, id 대조 필요
		Article article = parkInfoArticleDao.getArticle(articleTitle);
		if(article == null) {
			System.out.println("게시글 제목이 없습니다.");
			System.out.println("수정하실 게시글의 제목을 정확히 입력해주세요.");
			System.out.println();
			return;
		}
		System.out.printf("새로운 제목입력 >> ");
		String title = sc.nextLine();
		System.out.printf("새로운 내용입력 >> ");
		String body = sc.nextLine();
		parkInfoArticleDao.doArticleModify(title, body, article.id);
		System.out.println("게시글 수정이 완료되었습니다.");
		System.out.println();
	}
	
	public void doArticleDelete(String articleTitle, Scanner sc) { // 로그인 옵션,  id 대조 필요
		Article article = parkInfoArticleDao.getArticle(articleTitle);
		if(article == null) {
			System.out.println("게시글 제목이 없습니다.");
			System.out.println("삭제하실 게시글의 제목을 정확히 입력해주세요.");
			System.out.println();
			return;
		}

		while(true) {
			System.out.print("정말 게시글을 삭제하시겠습니까? (y/n) >> ");
			String yesOrNo = sc.nextLine();
			yesOrNo.toLowerCase();
			if (yesOrNo.length() != 1) {
				System.out.println("y 또는 n 을 입력 해주세요.");
				System.out.println();
				continue;
			} else {
				if(yesOrNo.equals("y")) {
					parkInfoArticleDao.doArticleDelete(article.id);
					System.out.println();
					System.out.println("게시글이 삭제되었습니다.");
					System.out.println();
					return;
				} else if (yesOrNo.equals("n")) {
					System.out.println();
					System.out.println("삭제가 취소되었습니다.");
					System.out.println();
					return;
				} else {
					System.out.println("y 또는 n 을 입력해주세요.");
					System.out.println();
					continue;
				}
			}
		}


	}

}
