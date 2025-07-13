package controller;

import java.io.IOException;
import java.util.List;

import dao.CitiesDao;
import dao.PrefecturesDao;
import entity.Cities;
import entity.Prefectures;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Form")
public class Form extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//DAOから都道府県情報を取得
		List<Prefectures> prefList = PrefecturesDao.getALLPrefectures();
		//System.out.println("prefList: " + prefList); //デバッグ用
		
		request.setAttribute("prefList", prefList);
		
		//DAOから市区町村情報を取得
		List<Cities> citiesList = CitiesDao.getALLCities();
		//System.out.println("citiesList: " + citiesList); //デバッグ用
		
		request.setAttribute("citiesList", citiesList);
		
		//JSPにフォワード
		request.getRequestDispatcher("/view/form.jsp").forward(request,response);
	}

}
