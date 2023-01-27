package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import dto.Top10Join;

public class Top10Repository {
	private static Top10Repository repository = new Top10Repository();
	public static Top10Repository getInstance() {
		return repository;
	}
	private Statement stmt = null;
	private ResultSet rs = null;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	public List<Top10Join> findTop10(Integer id) {
		List<Top10Join> top10Join = new ArrayList<>();
		conn = new DBConnection().getConn();
		
		String sql = "select "
				+ "d.title as dramaName,"
				+ "p.pName as VOD,"
				+ "d.episodeCount as numberOfEpisode,"
				+ "d.genre as genre,"
				+ "d.rating as rating,"
				+ "d.contentRating as contentRating,"
				+ "d.actor as cast,"
				+ "d.ranking as ranking "
				+ "from Drama as d "
				+ "inner join User as u on 2023 - u.birthYear >= left(d.contentRating, 2)"
				+ "left join platform as p on p.pId = d.originalNetwork "
				+ "where u.userId = ? "
				+ "limit 10";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
 				String dramaName = rs.getString("dramaName");
 				String VOD = rs.getString("VOD");
 				Integer numberOfEpisode = rs.getInt("numberOfEpisode");
 				String genre = rs.getString("genre");
 				String contentRating = rs.getString("contentRating");
 				float rating = rs.getFloat("rating");
 				String cast = rs.getString("cast");
 				String ranking = rs.getString("ranking");
 				Top10Join row = new Top10Join(dramaName, VOD, numberOfEpisode, contentRating, rating, genre, cast, ranking);
 				top10Join.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return top10Join;
	}
	public List<Top10Join> findTop10byCast(Integer id, String castName) {
		List<Top10Join> top10Join = new ArrayList<>();
		conn = new DBConnection().getConn();
		
		String sql = "select "
				+ "d.title as dramaName,"
				+ "p.pName as VOD,"
				+ "d.episodeCount as numberOfEpisode,"
				+ "d.genre as genre,"
				+ "d.rating as rating,"
				+ "d.contentRating as contentRating,"
				+ "d.actor as cast,"
				+ "d.ranking as ranking "
				+ "from Drama as d "
				+ "inner join User as u on 2023 - u.birthYear >= left(d.contentRating, 2)"
				+ "left join platform as p on p.pId = d.originalNetwork "
				+ "where u.userId = ? and d.actor like ? "
				+ "limit 10";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, "%" + castName + "%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
 				String dramaName = rs.getString("dramaName");
 				String VOD = rs.getString("VOD");
 				Integer numberOfEpisode = rs.getInt("numberOfEpisode");
 				String genre = rs.getString("genre");
 				String contentRating = rs.getString("contentRating");
 				float rating = rs.getFloat("rating");
 				String cast = rs.getString("cast");
 				String ranking = rs.getString("ranking");
 				Top10Join row = new Top10Join(dramaName, VOD, numberOfEpisode, contentRating, rating, genre, cast, ranking);
 				top10Join.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return top10Join;
	}
	
	public List<Top10Join> findTop10byPlatform(Integer id, String platformName) {
		List<Top10Join> top10Join = new ArrayList<>();
		conn = new DBConnection().getConn();
		
		String sql = "select "
				+ "d.title as dramaName,"
				+ "p.pName as VOD,"
				+ "d.episodeCount as numberOfEpisode,"
				+ "d.genre as genre,"
				+ "d.rating as rating,"
				+ "d.contentRating as contentRating,"
				+ "d.actor as cast,"
				+ "d.ranking as ranking "
				+ "from Drama as d "
				+ "inner join User as u on 2023 - u.birthYear >= left(d.contentRating, 2)"
				+ "left join platform as p on p.pId = d.originalNetwork "
				+ "where u.userId = ? and p.pName like ? "
				+ "limit 10";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, "%" + platformName + "%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
 				String dramaName = rs.getString("dramaName");
 				String VOD = rs.getString("VOD");
 				Integer numberOfEpisode = rs.getInt("numberOfEpisode");
 				String genre = rs.getString("genre");
 				String contentRating = rs.getString("contentRating");
 				float rating = rs.getFloat("rating");
 				String cast = rs.getString("cast");
 				String ranking = rs.getString("ranking");
 				Top10Join row = new Top10Join(dramaName, VOD, numberOfEpisode, contentRating, rating, genre, cast, ranking);
 				top10Join.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return top10Join;
	}
	
	public List<Top10Join> findTop10byGenre(Integer id, String genreName) {
		List<Top10Join> top10Join = new ArrayList<>();
		conn = new DBConnection().getConn();
		
		String sql = "select "
				+ "d.title as dramaName,"
				+ "p.pName as VOD,"
				+ "d.episodeCount as numberOfEpisode,"
				+ "d.genre as genre,"
				+ "d.rating as rating,"
				+ "d.contentRating as contentRating,"
				+ "d.actor as cast,"
				+ "d.ranking as ranking "
				+ "from Drama as d "
				+ "inner join User as u on 2023 - u.birthYear >= left(d.contentRating, 2)"
				+ "left join platform as p on p.pId = d.originalNetwork "
				+ "where u.userId = ? and d.genre like ? "
				+ "limit 10";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, "%" + genreName + "%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
 				String dramaName = rs.getString("dramaName");
 				String VOD = rs.getString("VOD");
 				Integer numberOfEpisode = rs.getInt("numberOfEpisode");
 				String genre = rs.getString("genre");
 				String contentRating = rs.getString("contentRating");
 				float rating = rs.getFloat("rating");
 				String cast = rs.getString("cast");
 				String ranking = rs.getString("ranking");
 				Top10Join row = new Top10Join(dramaName, VOD, numberOfEpisode, contentRating, rating, genre, cast, ranking);
 				top10Join.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return top10Join;
	}
}
