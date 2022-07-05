package mv_project.DATA;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class movieDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/test";
	
	public Connection open() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL,"sa","");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	
	// 전체데이터 가져오기
	public List<movieData> getAllPost() throws Exception{
		Connection conn = open();
		List<movieData> movieList = new ArrayList<>();
		
		String sql = "select * from movie ORDER BY id DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs) {
			while(rs.next()) {
				movieData n = new movieData();
				n.setMovieId(rs.getString("movie_id"));
				n.setTitle(rs.getString("TITLE"));
				n.setMovieImg(rs.getString("movie_img"));
				n.setRunningTime(rs.getInt("running_time")); 
				n.setGenre(rs.getString("genre"));
				n.setReleaseDate(rs.getString("release_date"));
				n.setCountry(rs.getString("country"));
				n.setStory(rs.getString("story"));
				n.setRate(rs.getString("rate"));
				n.setActorId(rs.getString("actor_id"));
				n.setReleaseYear(rs.getInt("release_year"));
				n.setContent(rs.getString("content"));
				n.setCompany(rs.getString("company"));
				n.setmovieUrl(rs.getString("movie_url"));
				n.setActor(getActorImg(rs.getString("MOVIE_ID")));
				
				movieList.add(n);
			}
			return movieList;			
		}
	}
	
	// 배우 필모그래피 목록 가져오기
	public List<movieData> getFilmography(String actor_id) throws Exception{
		Connection conn = open();
		List<movieData> movieList = new ArrayList<>();
		
		String sql = "SELECT * FROM movie WHERE actor_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, actor_id);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs) {
			while(rs.next()) {
				
				movieData n = new movieData();
				
				n.setMovieId(rs.getString("movie_id"));
				n.setTitle(rs.getString("TITLE"));
				n.setMovieImg(rs.getString("movie_img"));
				n.setRunningTime(rs.getInt("running_time")); 
				n.setGenre(rs.getString("genre"));
				n.setReleaseDate(rs.getString("release_date"));
				n.setCountry(rs.getString("country"));
				n.setStory(rs.getString("story"));
				n.setRate(rs.getString("rate"));
				n.setActorId(rs.getString("actor_id"));
				n.setReleaseYear(rs.getInt("release_year"));
				n.setContent(rs.getString("content"));
				n.setCompany(rs.getString("company"));
				n.setmovieUrl(rs.getString("movie_url"));
				n.setActor(getActorImg(rs.getString("MOVIE_ID")));
				
				movieList.add(n);
			}
			return movieList;			
		}
	}
	




	public List<actorData> getActorImg(String movieId) throws Exception {
		Connection conn = open();
		List<actorData> actorList = new ArrayList<>();
		
		
		String sql = "select actor, character, ACTOR_IMG, movie_id from actor where MOVIE_ID  = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, movieId);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs) {
			while(rs.next()) {
				actorData ad = new actorData();
				ad.setActor(rs.getString("actor"));
				ad.setCharacter(rs.getString("character"));
				ad.setActorImg(rs.getString("ACTOR_IMG"));
				ad.setMovieId(rs.getString("movie_id"));
				
				actorList.add(ad);
			}
			return actorList;
		}
	}
}
