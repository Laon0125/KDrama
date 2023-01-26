package db;

import java.sql.*;

public class DBConnection {
	private Connection conn;
	private final String id = "test";
	private final String password = "12345678";
	private final String url = "jdbc:mysql://localhost:3306/test";
	
	public DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			setConn(DriverManager.getConnection(url,id,password));
		;
		}
		catch (Exception e){
			System.out.println("실헹실패");
		}
		
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
