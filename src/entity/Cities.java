package entity;

public class Cities {
	private int cityId;
	private String cityName;
	private int prefId;
	
	public int getCityId() { return cityId; }
	public void setCityId(int cityId) { this.cityId = cityId; }
	
	public String getCityName() {return cityName; }
	public void setCityName(String cityName) { this.cityName = cityName; }
	
	public int getPrefId() { return prefId; }
	public void setPrefId(int prefId) { this.prefId = prefId; }

	@Override
	public String toString() {
		return "Cities{id=" + cityId + ", name=" + cityName + ", prefId=" + prefId + "}";
	}
}
