package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import db.DBConnection;

public class PlatformRepository {
	private static PlatformRepository repository = new PlatformRepository();
	public static PlatformRepository getInstance() {
		return repository;
	}
	private Statement stmt = null;
	private ResultSet rs = null;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	public void createPlatformTable() {
		
		String sql = "create table Platform("
				+ "pId int primary key auto_increment,"
				+ "pName varchar(10) not null"
				+ ")";
		
		String[] sqls = new String[]{"drop table if exists Platform ", sql};
		for(String str: sqls) {
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
	
	public void insertPlatform() {
		conn = new DBConnection().getConn();
		String sql = "insert into Platform(pName)"
				+ "values(?)";
		List<String> p = Arrays.asList("Netflix", "Wavve", "Watcha", "TVING");
		try {
			pstmt = conn.prepareStatement(sql);
			for(int i = 0; i < p.size(); i++) {
				pstmt.setString(1, p.get(i));
				pstmt.executeUpdate();				
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
