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

@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 前ページからデータを受け取る
        String userIdStr = request.getParameter("userId");
        int userId = 0;
        try {
        	userId = Integer.parseInt(userIdStr);
        } catch (NumberFormatException e) {
        	request.setAttribute("message", "ユーザーIDが正しく取得できませんでした。");
        	request.getRequestDispatcher("/view/update.jsp").forward(request, response);
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
        
        request.getRequestDispatcher("/view/update.jsp").forward(request, response);
    }

	//更新処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		request.setCharacterEncoding("UTF-8");
		
		//userIdをintで受け取る
		int userId = 0;
		try {
		 userId = Integer.parseInt(request.getParameter("userId")); 
		} catch (NumberFormatException e) {
		 request.setAttribute("message", "ユーザーIDが正しく取得できませんでした。");
		 request.getRequestDispatcher("/view/update.jsp").forward(request, response);
		 return;
		}
		
		//名前と電話番号も受け取る
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		
		//入力値バリデーション
		if (name == null || name.trim().isEmpty() || tel == null || tel.trim().isEmpty()) {
			request.setAttribute("message", "名前と電話番号は必須です。");
	        request.getRequestDispatcher("/view/update.jsp").forward(request, response);
	        return;
	    }
		
		
		//市区町村IDをリクエストパラメータから取得 (ex."cityId" → 102)
		int cityId = 0;
		try {
			cityId = Integer.parseInt(request.getParameter("cityId"));
		} catch (NumberFormatException e) {
			request.setAttribute("message", "市区町村の選択が正しくありません。" );
			request.getRequestDispatcher("/view/update.jsp").forward(request,  response);
			return;
		}
		
		Users user = new Users();
		user.setUserId(userId);
		user.setName(name);
		user.setTel(tel);
		user.setCityId(cityId);
		
		/*デバッグ用
		System.out.println("userId: " + userId);
		System.out.println("name: " + name);
		System.out.println("tel: " + tel);
		System.out.println("cityId; " + cityId);
		*/
		
		int userResult = 0;
        try {
            userResult = UsersDao.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "システムエラーが発生しました。");
            request.getRequestDispatcher("/view/update.jsp").forward(request, response);
            return;
        }
        
        if (userResult > 0) {
        	//更新後のユーザー情報を再取得
        	Users updatedUser = UsersDao.getUpdatedUserById(userId);
        	
        	//JSPで使えるようにリクエストに設定
        	request.setAttribute("user", updatedUser);
        	
        	//成功画面にフォワード
			request.getRequestDispatcher("/view/success_update.jsp").forward(request, response);
			
			//System.out.println("DEBUG: updateUser result = " + userResult); //デバッグ用
			
			return;
		} else {
			//更新失敗でエラーメッセージを表示
			request.setAttribute("message", "更新に失敗しました。");
			
			request.getRequestDispatcher("/view/update.jsp").forward(request,  response);
			return;
		}	
        
	}
}
