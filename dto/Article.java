package dto;

public class Article {
	
	public int id;
	public String regDate;
	public String title;
	public String body;
	public int memberId;
	public int hit;
	public int recommend;
	
	public Article(int id, String regDate, String title, String body, int memberId, int hit, int recommend) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.memberId = memberId;
		this.hit = hit;
		this.recommend = recommend;
	}

}
