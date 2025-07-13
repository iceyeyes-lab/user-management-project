package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConnect;
import entity.Prefectures;

public class PrefecturesDao {
	//セレクトボックス用
	public static List<Prefectures> getALLPrefectures(){
		List<Prefectures> list = new ArrayList<>();
		try {Connection conn = DBConnect.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT pref_id as prefId, pref_name as prefName FROM prefectures");
			
			while (rs.next()) {
				Prefectures pref = new Prefectures();
				pref.setPrefId(rs.getInt("prefId"));
				pref.setPrefName(rs.getString("prefName"));
				list.add(pref);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/*adrIDから住所を確認する用
	public static Prefectures getAddressById(String adrID) {
		Prefectures address = null;
		try {Connection conn = DBConnect.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM address WHERE adrID = ?");
			
			pstmt.setString(1,adrID);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				address = new Prefectures();
				address.setAdrID(rs.getString("adrID"));
				address.setADR(rs.getString("ADR"));
			}
			rs.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return address;
	}*/

}
