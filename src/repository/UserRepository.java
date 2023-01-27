package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import dto.User;

public class UserRepository {
	private static UserRepository repository = new UserRepository();
	public static UserRepository getInstance() {
		return repository;
	}
	private Statement stmt = null;
	private ResultSet rs = null;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	public void createUserTable() {
		
		String sql = "create table User("
				+ "userId int primary key auto_increment,"
				+ "userName varchar(255) not null,"
				+ "vod int,"
				+ "birthYear int not null"
				+ ")";
		String preInsert = "insert into User(userName, vod, birthYear)" 
				+ "values " + "('진', '3','1998'),"
				+ "('김진우', '4','1995')," + "('김한휘', '2', '2008')," + "('지누', '3','2020')," + "('라라', '3','2020')";
		
		String[] sqls = new String[]{"drop table if exists User ", sql,preInsert};
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
	
	public void insertUser(String userName, int vod, int birthYear) {
		conn = new DBConnection().getConn();
		String sql = "insert into user(userName, vod, birthYear)"
				+ "values(?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setInt(2, vod);
			pstmt.setInt(3, birthYear);
			pstmt.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User findUser(int userId) { 
		User user = new User();
		conn = new DBConnection().getConn();
		String sql = "select u.userName, p.pName, u.birthYear from "
				+ "user as u "
				+ "left join platform as p on p.pId = u.VOD "
				+ "where userId = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				user = new User(rs);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
