package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConnect;
import entity.Cities;

public class CitiesDao {
	public static List<Cities> getALLCities() {
		List<Cities> list = new ArrayList<>();
		String sql = """
				SELECT
					city_id AS cityId,
					city_name AS cityName,
					pref_id AS prefId
				FROM 
					cities
		""";
		
		try (Connection conn = DBConnect.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			
			while (rs.next()) {
				Cities city = new Cities();
				city.setCityId(rs.getInt("cityId"));
				city.setCityName(rs.getString("cityName"));
				city.setPrefId(rs.getInt("prefId"));
				list.add(city);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		return list;
	}
}
