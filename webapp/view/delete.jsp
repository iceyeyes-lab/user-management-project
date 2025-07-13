<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entity.Users" %>
<%@ page import="entity.Prefectures" %>
<%@ page import="entity.Cities" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認画面</title>
</head>
<body>
	<h2>ユーザー削除確認</h2>
	
	<%
		//ユーザー情報と都道府県・市区町村のリストをリクエストから取得
		Users user = (Users) request.getAttribute("user");
		List<Prefectures> prefList = (List<Prefectures>) request.getAttribute("prefList");
		List<Cities> citiesList = (List<Cities>) request.getAttribute("citiesList");
		
		//表示用の住所文字列(都道府県＋市区町村)を初期化
		String currentPrefName = "";
		String currentCityName = "";
		
		if (user != null && citiesList != null && prefList != null) {
			int selectedCityId = user.getCityId();
			int selectedPrefId = -1;
			
			//市区町村IDから市区町村名と都道府県IDを取得
			for (Cities c : citiesList) {
				if (c.getCityId() == selectedCityId) {
					currentCityName = c.getCityName();
					selectedPrefId = c.getPrefId();
					break;
				}
			}
			
			//都道府県IDから都道府県名を取得
			for (Prefectures p : prefList) {
				if (p.getPrefId() == selectedPrefId) {
					currentPrefName = p.getPrefName();
					break;
				}
			}
	%>
	
	<!--  削除確認メッセージと登録情報の表示 -->
	<p>ご登録情報を削除します。本当によろしいですか？</p>
	<ul>
		<li>名前: <%= user.getName() %></li>
		<li>住所: <%= currentPrefName + " " + currentCityName %></li>
		<li>電話番号: <%= user.getTel() %></li>		
	</ul>
	
	<!-- 削除処理用フォーム -->
	<form action="<%= request.getContextPath()  %>/Delete" method="post">
	<input type="hidden" name="id" value="<%= user.getUserId() %>">
	<button type="submit">削除する</button>
	<button type="button" onclick="history.back();">キャンセル</button></form>
	<% 
		} else {
	%>
		<!-- データが取得できなかった場合のメッセージ -->
		<p>削除対象のユーザー情報が見つかりません。</p>
	<%
		}
	%>
</body>
</html>