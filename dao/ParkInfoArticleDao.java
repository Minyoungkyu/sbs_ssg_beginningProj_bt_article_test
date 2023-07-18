package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.Article;

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

	public ParkInfoArticleDao(int parkId) {
		this.parkId = parkId;
		this.articles = new ArrayList<>();
		try {
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
		return articles;
	}

	public ArrayList<Article> getSearchedArticle(String searchKey) {
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
		for(int i = 0; i < articles.size(); i++) {
			if(articles.get(i).title.equals(articleTitle)) {
				increaseHit(articles.get(i).id,i);
				return articles.get(i);
			}
		}
		return null;
	}
	
	
	
	
	private void increaseHit(int id, int index) {
		try {
			statement.executeUpdate("UPDATE article SET hit = hit + 1 WHERE id = " + id);
		} catch ( Exception e) {}
		articles.get(index).hit++;
		
	}
}






