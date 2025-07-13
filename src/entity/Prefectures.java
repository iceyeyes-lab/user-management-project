package entity;

public class Prefectures {
	private int prefId;
	private String prefName;
	
	public int getPrefId() { return prefId; }
	public void setPrefId(int prefId) { this.prefId = prefId; }
	
	public String getPrefName() {return prefName; }
	public void setPrefName(String prefName) { this.prefName = prefName; }

	@Override
	public String toString() {
		return "Prefectures{id=" + prefId + ", name='" + prefName + "'}";
	}
}
