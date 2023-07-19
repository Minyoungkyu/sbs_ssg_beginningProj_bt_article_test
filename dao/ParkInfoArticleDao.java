package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.Article;
import dto.ArticleReply;

public class ParkInfoArticleDao {
	private String url = "jdbc:mysql://localhost:3306/baseball_tonight";
	private String userName = "root";
	private String password = "";
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private int parkId;
	private ArrayList<Article> articles;
	private ArrayList<Article> searchedArticles;
	private ArrayList<ArticleReply> replys;

	public ParkInfoArticleDao(int parkId) {
		this.parkId = parkId;
		try {
			this.articles = new ArrayList<>();
			connection = DriverManager.getConnection(url, userName, password);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from article where parkId = " + parkId + " ORDER BY hit DESC"); // 조회수 높은 순
			while (resultSet.next()) {
				int id = resultSet.getInt("id"); 
				String regDate = resultSet.getString("regDate");
				String title = resultSet.getString("title");
				String body = resultSet.getString("body");
				int memberId = resultSet.getInt("memberId"); // 나중에 query문 join 으로 바꾸고 String 으로 작성자 닉네임 새로 받아야함.
				int hit = resultSet.getInt("hit");
				int recommend = resultSet.getInt("recommend");

				articles.add(new Article(id, regDate, title, body, memberId, hit, recommend));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Article> getArticleList() {
		reSet();
		return articles;
	}

	public ArrayList<Article> getSearchedArticle(String searchKey) {
		reSet();
		this.searchedArticles = new ArrayList<>();
		for (Article a : articles) {
			if (a.title.contains(searchKey))
				searchedArticles.add(a);
			else if (a.body.contains(searchKey))
				searchedArticles.add(a);
		}
		return searchedArticles;
	}
	
	public Article getArticle(String articleTitle)	{
		reSet();
		for(int i = 0; i < articles.size(); i++) {
			if(articles.get(i).title.equals(articleTitle)) {
				increaseHit(articles.get(i).id);
				return articles.get(i);
			}
		}
		return null;
	}

	public void doArticleWrite(String title, String body, int parkId) {
		try {
			statement.executeUpdate("INSERT INTO article\n" +
					"SET regDate = NOW(),\n" +
					"title = '" + title + "',\n" +
					"`body` = '" + body + "',\n" +
					"memberId = 2,\n" + // 회원기능 추가 후 변경
					"hit = 0,\n" +
					"recommend = 0,\n" +
					"parkId = " + parkId + ";");
		} catch(SQLException e){}
	}

	public void doArticleModify(String title, String body, int articleId) {
		try {
			statement.executeUpdate("UPDATE article\n" +
					"SET title = '" + title + "',\n" +
					"`body` = '" + body + "'\n" +
					"WHERE id = " + articleId + ";");
		} catch(SQLException e){}
	}

	public void doArticleDelete(int articleId) {
		try {
			statement.executeUpdate("DELETE FROM article WHERE id = " + articleId + ";");
		} catch (SQLException e) {}
	}
	
	public void doArticleReplyWrite(String body, int articleId) { // memberId 고쳐야함!!!
		try {
			statement.executeUpdate("INSERT INTO article_reply\r\n"
					+ "SET regDate = NOW(),\r\n"
					+ "`body` = '" + body + "',\r\n"
					+ "memberId = 1,\r\n" // memberId 수정
					+ "articleId = " + articleId + ",\r\n"
					+ "recommend = 0;");
		} catch (SQLException e) {}
	}
	
	
	private void increaseHit(int id) {
		try {
			statement.executeUpdate("UPDATE article SET hit = hit + 1 WHERE id = " + id);
		} catch ( Exception e) {}
	} // 조회수 증가 함수

	public void increaseRecommend(int id) {
		try {
			statement.executeUpdate("UPDATE article SET recommend = recommend + 1 WHERE id = " + id);
		} catch ( Exception e) {}
	} // 추천수 증가 함수

	public void decreaseRecommend(int id) {
		try {
			statement.executeUpdate("UPDATE article SET recommend = recommend - 1 WHERE id = " + id);
		} catch ( Exception e) {}
	} // 추천수 감소 함수

	public ArrayList<ArticleReply> getArticleReplyList(int articleId) {
		try {
			this.replys = new ArrayList<>();
			connection = DriverManager.getConnection(url, userName, password);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT \r\n"
					+ "AR.id,\r\n"
					+ "AR.regDate,\r\n"
					+ "AR.body,\r\n"
					+ "AR.memberId,\r\n"
					+ "AR.regDate\r\n"
					+ "FROM article AS A \r\n"
					+ "INNER JOIN article_reply AS AR\r\n"
					+ "ON A.id = AR.articleId\r\n"
					+ "WHERE A.id = " + articleId + ";"); 
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String regDate = resultSet.getString("regDate");
				String body = resultSet.getString("body");
				int memberId = resultSet.getInt("memberId"); // 나중에 query문 join 으로 바꾸고 String 으로 작성자 닉네임 새로 받아야함.

				replys.add(new ArticleReply(id, regDate, body, memberId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return replys;
	}
	
	private void reSet() {
		try {
			this.articles = new ArrayList<>();
			connection = DriverManager.getConnection(url, userName, password);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from article where parkId = " + parkId + " ORDER BY hit DESC"); // 조회수 높은 순
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String regDate = resultSet.getString("regDate");
				String title = resultSet.getString("title");
				String body = resultSet.getString("body");
				int memberId = resultSet.getInt("memberId"); // 나중에 query문 join 으로 바꾸고 String 으로 작성자 닉네임 새로 받아야함.
				int hit = resultSet.getInt("hit");
				int recommend = resultSet.getInt("recommend");

				articles.add(new Article(id, regDate, title, body, memberId, hit, recommend));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // articles 새로 세팅.
}






