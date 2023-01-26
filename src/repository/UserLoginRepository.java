package repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBConnection;

import dto.UserLogin;


public class UserLoginRepository {
	private static UserLoginRepository repository = new UserLoginRepository();
	public static UserLoginRepository getInstance() {
		return repository;
	}
	private Statement stmt = null;
	private ResultSet rs = null;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	
	public void createUserLoginTable(){
		String sql = "create table UserLogin ("
				+ "LoginId int primary key auto_increment, "
				+ "Id varchar(30) not null,"
				+ "password varchar(30) not null"
				+ ")";
		String[] sqls = new String[]{"drop table if exists UserLogin ", sql};
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
	
	public boolean isExistUserId(String id) {
		String sql = "select Id from UserLogin where Id = \"" + id + "\"";
		boolean isUser = false;
		try {
			conn = new DBConnection().getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				UserLogin userLogin = new UserLogin(rs);
				isUser = true;
			}
			conn.close();
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return isUser;
	}
	public boolean isExistUserIdPassword(String id, String password) {
		String sql = "select Id from UserLogin where Id = \"" + id + "\"" + " and password = " + "\"" + password + "\"";
		boolean isUserPassword = false;
		try {
			conn = new DBConnection().getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				UserLogin userLogin = new UserLogin(rs);
				isUserPassword = true;
			}
			conn.close();
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return isUserPassword;
	}
	public void insertUserLogin(String id, String password) {
		conn = new DBConnection().getConn();
		String sql = "insert into UserLogin(id, password) "
				+ "values (?,?)";
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.executeUpdate();
			conn.commit();
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}
			catch (SQLException el) {
				el.printStackTrace();
			}
			finally {
//				conn.coneect
			}
			
		}
	}
	
}
