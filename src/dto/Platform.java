package dto;

public class Platform {
	int pId;
	String pName;
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public Platform(int pId, String pName) {
		super();
		this.pId = pId;
		this.pName = pName;
	}
	
}