package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
	
	public void showArticleDetail(String articleTitle)	{
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
		
		
		
	}
	
	public void doArticleWrite() {
		
		
	}
	
	public void doArticleModify() {
		
	}
	
	public void doArticleDelete() {
		
	}

}
