package mv_project.DATA;

import java.util.List;

public class movieData {
	private String movieId;
	private String title;
	private String movieImg;
	private String genre;
	private String releaseDate;
	private String country;
	private String story;
	private String rate;
	private String actorId;
	private String content;
	private String company;
	private String movieUrl;
	private int runningTime;
	private int releaseYear;
	private int type;
	private List<actorData> actor;
	
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMovieImg() {
		return movieImg;
	}
	public void setMovieImg(String movieImg) {
		this.movieImg = movieImg;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getActorId() {
		return actorId;
	}
	public void setActorId(String actorId) {
		this.actorId = actorId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getmovieUrl() {
		return movieUrl;
	}
	public void setmovieUrl(String movieUrl) {
		this.movieUrl = movieUrl;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getRunningTime() {
		return runningTime;
	}
	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<actorData> getActor() {
		return actor;
	}
	public void setActor(List<actorData> actor) {
		this.actor = actor;
	}
	
	
}
