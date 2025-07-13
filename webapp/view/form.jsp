<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Prefectures" %>
<%@ page import="entity.Cities" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録画面</title>
<style>
   input,select{
     width: 40%;
     padding: 1px;
     box-sizing: border-box;
     vertical-align: top;
     height: 30px;
   }
  </style>
</head>
<body>
<h3>入力フォーム</h3>

<!-- メッセージ表示(登録完了・エラーなど) -->
<p>
<%
		String message = (String) request.getAttribute("message");
	if (message != null) {
		out.print(message);
	}
%>
</p>
<form action="<%= request.getContextPath() %>/Insert" method="post">

<!-- 名前の入力 -->
<input type="text" name="name" placeholder="名前を入力してください"><br><br>

<!-- 都道府県の選択 -->
<!-- Java側で取得したprefListをループ -->
<select id="prefSelect" name="prefId">
	<option value="">都道府県を選択してください</option>
	<%
		List<Prefectures> prefList = (List<Prefectures>) request.getAttribute("prefList");
		for (Prefectures pref : prefList) {
	%>
			<option value="<%= pref.getPrefId() %>"><%= pref.getPrefName() %></option>
	<% } %>
</select><br><br>

<!--  都道府県に連動した市区町村の選択 -->
<select id="citySelect" name="cityId">
<option value="">市区町村を選択してください</option>
</select><br><br>

<!-- JSを呼び出す -->
<script src="<%= request.getContextPath() %>/js/address_select.js"></script>

<script>
	const cityMap = {};
	<%	
		List<Cities> citiesList = (List<Cities>) request.getAttribute("citiesList");
		for (Cities city : citiesList) {
	%>
		if (!cityMap[<%= city.getPrefId() %>]) {
			cityMap[<%= city.getPrefId() %>] = [];
		}
		cityMap[<%= city.getPrefId() %>].push({ id: <%= city.getCityId() %>, name: "<%= city.getCityName() %>" });
	<%
	}
	%>

	initializeAddress();
</script>

<!-- 電話番号の入力 -->
<input type="text" name="tel" placeholder="電話番号を入力してください"><br><br>

<!-- 登録ボタン -->
<button type="submit" name="button">登録する</button>
</form>

<!-- 戻るボタン -->
<form action="<%= request.getContextPath() %>/InfoSelect" method="post">
<button type="submit" name="button">戻る</button>
</form>

</body>
</html>