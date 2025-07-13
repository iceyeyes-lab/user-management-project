package entity;

public class Users {
	private int userId;
	private String name;
	private String address;
	private String tel;
	private int cityId;
	
	public int getUserId() { return userId; }
	public void setUserId(int userId) { this.userId = userId;}
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }
	
	public String getTel() { return tel;}
	public void setTel(String tel) { this.tel = tel; }
	
	public int getCityId() { return cityId; }
	public void setCityId(int cityId) { this.cityId = cityId; }

	@Override
	public String toString() {
		return "Cities{id=" + userId + ", name=" + name + ", tel=" + tel + ", cityId=" + cityId + "}";
	}
}
