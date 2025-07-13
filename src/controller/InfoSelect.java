package controller;

import java.io.IOException;
import java.util.List;

import dao.UsersDao;
import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/InfoSelect")
public class InfoSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//DAOからテーブルデータを貰う
		List<Users> userList = UsersDao.getAllUsers();
		
		//System.out.println("UserList: " + userList); //デバッグ出力用１
		//System.out.println("userList is null? " + (userList == null)); //デバッグ出力用２
		
		//info_table.jspへ
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("/view/info_table.jsp").forward(request, response);
	}
}
