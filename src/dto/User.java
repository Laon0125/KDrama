package dto;

import java.sql.ResultSet;

public class User {
	int userId;
	String userName;
	String vod;
	int birthYear;
	public User() {}
	
	public User(ResultSet rs) {
		try {
			this.userName = rs.getString("userName");
			this.vod = rs.getString("pName");
			this.birthYear = rs.getInt("birthYear");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public User(int userId, String userName, int dramaId, String vod, String birth) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.vod = vod;
		this.birthYear = birthYear;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getVod() {
		return vod;
	}
	public void setVod(String vod) {
		this.vod = vod;
	}
	public User(int userId, int dramaId, String birth) {
		super();
		this.userId = userId;
		this.birthYear = birthYear;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBirth() {
		return birthYear;
	}
	public void setBirth(String birth) {
		this.birthYear = birthYear;
	}
	
	@Override
	public String toString() {
		return "이름=" + userName + ", 구독중인 서비스=" + vod
				+ ", 출생년도=" + birthYear;
	}
}








