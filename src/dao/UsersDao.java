package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConnect;
import entity.Users;

public class UsersDao {
	//テーブル表示用
	public static List<Users> getAllUsers() {
		List<Users> list = new ArrayList<>();
		String sql = """
				SELECT
					u.user_id AS userId,
					u.user_name AS name,
					CONCAT(p.pref_name, c.city_name) AS address,
					u.tel AS tel
				FROM
					users u
				JOIN
					cities c ON u.city_id =  c.city_id
				JOIN
					prefectures p ON c.pref_id = p.pref_id
				ORDER BY
					u.user_id
				""";
		try (Connection conn = DBConnect.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			
			while (rs.next()) {
				Users user = new Users();
				user.setUserId(rs.getInt("userId"));
				user.setName(rs.getString("name"));
				user.setAddress(rs.getString("address"));
				user.setTel(rs.getString("tel"));
				list.add(user);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//更新フォームでIDごとの情報表示する用
	public static Users getUserById(int userId) {
		Users user = null;
		String sql = """
		 		SELECT
		 		    user_id AS userId,
		 			user_name AS name,
		 			tel,
		 			city_id AS cityId
		 		FROM
		 			users
		 		WHERE
		 			user_id = ?
		 		""";
		
		try (Connection conn = DBConnect.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setInt(1,  userId);
			try(ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					user = new Users();
					user.setUserId(rs.getInt("userId"));
					user.setName(rs.getString("name"));
					user.setTel(rs.getString("tel"));
					user.setCityId(rs.getInt("cityId"));
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	//新規登録用
	public int insertUser(Users user) {
		int result = 0;
		String sql = "INSERT INTO users (user_name, tel, city_id) VALUES (?,?,?)";
		
		try (Connection conn = DBConnect.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1,  user.getName());
			pstmt.setString(2,  user.getTel());
			pstmt.setInt(3,  user.getCityId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLエラー: " + e.getMessage());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			System.err.println("クラスが見つかりません: " + e1.getMessage());
		}
		return result;
	}
	
	//更新用
	public static int updateUser(Users user) {
		int result = 0;
		String sql = "UPDATE users SET user_name = ?, tel = ?, city_id = ? WHERE user_id = ?";
		
		try (Connection conn = DBConnect.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getTel());
			pstmt.setInt(3, user.getCityId());
			pstmt.setInt(4,user.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//削除用
	public static int deleteUserById(int id) {
		int result = 0;
		String sql = "DELETE FROM users WHERE user_id = ?";
		
		try (Connection conn = DBConnect.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setInt(1,  id);
			result = pstmt.executeUpdate();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

}
