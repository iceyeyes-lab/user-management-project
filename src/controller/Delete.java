package controller;

import java.io.IOException;
import java.util.List;

import dao.CitiesDao;
import dao.PrefecturesDao;
import dao.UsersDao;
import entity.Cities;
import entity.Prefectures;
import entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//確認メッセージ用
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//前ページからIDデータを受け取る
        String userIdStr = request.getParameter("userId");
        int userId = 0;
        try {
        	userId = Integer.parseInt(userIdStr);
        } catch (NumberFormatException e) {
        	request.setAttribute("message", "ユーザーIDが正しく取得できませんでした。");
        	request.getRequestDispatcher("/view/delete.jsp").forward(request, response);
        	return;
        }
        
        //UsersDaoからユーザー情報を取得
        Users user = UsersDao.getUserById(userId);
        request.setAttribute("user", user);
        
        //PrefecturesDaoから都道府県情報を取得
      	List<Prefectures> prefList = PrefecturesDao.getALLPrefectures();
      	//System.out.println("prefList: " + prefList); //デバッグ用
      	request.setAttribute("prefList", prefList);
      	
      	//CitiesDaoから市区町村情報を取得
      	List<Cities> citiesList = CitiesDao.getALLCities();
      	//System.out.println("citiesList: " + citiesList); //デバッグ用
      	request.setAttribute("citiesList", citiesList);
		
		request.getRequestDispatcher("/view/delete.jsp").forward(request,  response);
	}
	
	//削除実行
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String idStr = request.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			request.setAttribute("message", "ユーザーIDが不正です。");
			request.getRequestDispatcher("/view/delete.jsp").forward(request, response);
			return;
		}
		
		int result = UsersDao.deleteUserById(id);
		
		if (result > 0) {
			//削除成功で削除成功ページにフォワード
			request.getRequestDispatcher("/view/success_delete.jsp").forward(request, response);
		} else {
			//削除失敗でエラーメッセージを表示
			request.setAttribute("message", "削除に失敗しました。");
			
			request.getRequestDispatcher("/view/delete.jsp").forward(request,  response);
		}
	
	}
}
