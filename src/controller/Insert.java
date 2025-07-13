package controller;

import java.io.IOException;

import dao.UsersDao;
import entity.Users;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("InsertサーブレットのdoPostが呼ばれました");
		
		//フォームからデータを受け取る
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		
		// 入力チェック
		if (name == null || name.isEmpty() || tel == null || tel.isEmpty()) {
			request.setAttribute("message", "名前と電話番号は必須です。");
			request.getRequestDispatcher("/form.jsp").forward(request, response);
			return;
		}
		
		//フォームから受け取った文字列を数値に変換。空だったり数値でなかったらエラーメッセージが表示される。
		int cityId = 0;
		try {
			cityId = Integer.parseInt(request.getParameter("cityId"));
		} catch (NumberFormatException e) {
			request.setAttribute("message", "市区町村の選択が正しくありません。" );
			RequestDispatcher dispatcher = request.getRequestDispatcher("/form.jsp");
			dispatcher.forward(request,  response);
			return;
		}
		
		Users user = new Users ();
		user.setName(name);
		user.setTel(tel);
		user.setCityId(cityId);
		
		//DAOにデータを送る
		UsersDao dao = new UsersDao();
		int result = dao.insertUser(user);
		if(result > 0) {
			request.setAttribute("message", "新規登録が完了しました！");
		} else {
			request.setAttribute("message", "登録に失敗しました。");
		}
		
		//送り先はあとで変更するかも
		response.sendRedirect(request.getContextPath() + "/InfoSelect");
	}
}
