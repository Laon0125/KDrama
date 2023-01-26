package dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLogin {
	
	private Integer loginId;
	public Integer getLoginId() {
		return loginId;
	}


	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	private String id;
	private String password;
	public UserLogin(Integer loginId, String id, String password ){
		// TODO Auto-generated constructor stub
		this.loginId = loginId;
		this.id = id;
		this.password= password;
	}
	
	
	public UserLogin() {
		// TODO Auto-generated constructor stub
	}
	public UserLogin(ResultSet rs) {
		// TODO Auto-generated constructor stub
		try {
			
			this.id = rs.getString("Id");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public String toString() {
		return "\n"
				+ "userLogin \n"
				+ "[id=" + loginId + ", \n"
				+ "id=" + id + ", \n"
				+ "password" + password + ", \n";
	}


	
}
