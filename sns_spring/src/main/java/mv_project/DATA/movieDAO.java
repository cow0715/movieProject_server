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
	
	
	
	
	public List<movieData> getAllPost() throws Exception{
		Connection conn = open();
		List<movieData> movieList = new ArrayList<>();
		
		String sql = "select * from movie";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs) {
			while(rs.next()) {
				movieData n = new movieData();
				n.setTitle(rs.getString("TITLE"));
				n.setStory(rs.getString("STORY"));
				//n.setRunningTime(rs.getInt("RUNNING_TIME")); 
				n.setGenre(rs.getString("GENRE")); 
				n.setReleaseDate(rs.getString("RELEASE_DATE"));
				n.setCountry(rs.getString("COUNTRY"));
				n.setRate(rs.getString("RATE"));
				n.setmovieUrl(rs.getString("MOVIE_IMG"));
				n.setActor(getActorImg(rs.getString("MOVIE_ID")));
				
				movieList.add(n);
			}
			return movieList;			
		}
	}




	public List<actorData> getActorImg(String movieId) throws Exception {
		Connection conn = open();
		List<actorData> actorList = new ArrayList<>();
		
		
		String sql = "select actor, character, ACTOR_IMG from actor where MOVIE_ID  = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, movieId);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs) {
			while(rs.next()) {
				actorData ad = new actorData();
				ad.setActor(rs.getString("actor"));
				ad.setCharacter(rs.getString("character"));
				ad.setActorImg(rs.getString("ACTOR_IMG"));
				
				actorList.add(ad);
			}
			return actorList;
		}
	}
}
