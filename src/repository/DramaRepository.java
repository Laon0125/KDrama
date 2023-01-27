package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import db.DBConnection;
import dto.Drama;
import dto.Top10Join;

public class DramaRepository {

	private static DramaRepository repository = new DramaRepository();

	public static DramaRepository getInstance() {
		return repository;
	}

	private Statement stmt = null;
	private ResultSet rs = null;
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public void createDramaTable() {

		String sql = "create table Drama(" + "dramaId int primary key auto_increment," + "title varchar(255) not null,"
				+ "onDate varchar(255)," + "releaseYear int," + "originalNetwork varchar(20)," + "onDay varchar(255),"
				+ "episodeCount int," + "duration varchar(50)," + "contentRating varchar(255)," + "rating float,"
				+ "genre varchar(255)," + "tags varchar(250)," + "director varchar(30)," + "actor varchar(250),"
				+ "productionCompany varchar(255)," + "ranking varchar(7)" + ")";
		String[] sqls = new String[] { "drop table if exists Drama ", sql };
		for (String str : sqls) {
			try {
				conn = new DBConnection().getConn();
				stmt = conn.createStatement();
				stmt.executeUpdate(str);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public String insertTable(List<String> list) {
		String sql = "insert into Drama(" + "title," + "onDate," + "releaseYear," + "originalNetwork," + "onDay,"
				+ "episodeCount," + "duration," + "contentRating," + "rating," + "genre," + "tags," + "director,"
				+ "actor," + "productionCompany," + "ranking" + ") " + " values (" + "?," + "?," + "?," + "?," + "?,"
				+ "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?," + "?" + ")";

		try {

			conn = new DBConnection().getConn();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < 15; i++) {
				pstmt.setString(i + 1, list.get(i).replace("\"", ""));
			}
			pstmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "추가 성공";
	}

	public void updateOriginalNetwork() {
		String safeMode = "SET SQL_SAFE_UPDATES = 0;";
		String sql = "update Drama " + "set originalNetwork=" + "replace(originalNetwork, ?, ?)";
		try {
			conn = new DBConnection().getConn();
			pstmt = conn.prepareStatement(sql);
			stmt = conn.createStatement();
			stmt.executeUpdate(safeMode);
			List<String> Vod = Arrays.asList("Netflix", "Wavve", "KBS2", "MBC", "SBS", "tvN", "ENA", "jTBC", "Viki",
					"OCN", "Hulu", "iQiyi");
			for (int i = 0; i < Vod.size(); i++) {
				String str = Vod.get(i);
				pstmt.setString(1, str);
				if (str.equals("Netflix"))
					pstmt.setString(2, "1");
				else if (str.equals("Wavve"))
					pstmt.setString(2, "2");
				else if (str.equals("KBS2") || str.equals("MBC") || str.equals("SBS"))
					pstmt.setString(2, "3");
				else {
					pstmt.setString(2, "4");
				}
				pstmt.executeUpdate();
			}
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Top10Join> ascedingRating(Integer id) {
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
				+ "order by rating ASC "
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
	public List<Top10Join> descedingRating(Integer id) {
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
				+ "order by rating desc "
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
}
