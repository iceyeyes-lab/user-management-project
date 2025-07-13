<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entity.Users" %>
<%@ page import="entity.Prefectures" %>
<%@ page import="entity.Cities" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録情報の変更</title>
<style>
   textarea,select{
     width: 40%;
     padding: 1px;
     box-sizing: border-box;
     vertical-align: top;
     height: 30px;
  </style>
</head>
<body>
<h3>登録情報変更フォーム</h3>
<p>
<%
String message = (String) request.getAttribute("message");
if (message != null) {
%>
<div style="color: red;"><%=message%></div>
<%
}
%>
</p>
<form action="<%=request.getContextPath()%>/Update" method="post">

<%
Users user = (Users) request.getAttribute("user");
%>
<!-- userがnullで無いか確認 -->
<%
if (user != null) {
%>
<!-- userIdを裏で送る -->
<input type="hidden" name="userId" value="<%= user.getUserId()%>">
<%
}
%>

<!-- 名前 -->
<p>現在の名前: <%=request.getAttribute("user") != null ? ((Users) request.getAttribute("user")).getName() : ""%></p>
<textarea name="name"><%=request.getAttribute("user") !=null ? ((Users) request.getAttribute("user")).getName() : ""%></textarea><br><br>

<!-- 住所 -->
<%
//都道府県・市区町村のリストをクエストから取得
List<Prefectures> prefList = (List<Prefectures>) request.getAttribute("prefList");
List<Cities> citiesList = (List<Cities>) request.getAttribute("citiesList");

//表示用の住所文字列（都道府県＋市区町村）を初期化
String currentPrefName = "";
String currentCityName = "";
int selectedPrefId = -1;
int selectedCityId = -1;

if (user != null && citiesList != null) {
	selectedCityId = user.getCityId();
	
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
}
%>
<p>現在の住所: <%= currentPrefName + " " + currentCityName %></p>

<!-- 都道府県の選択 -->
<select id="prefSelect" name="prefId">
	<option value="">都道府県を選択してください</option>
	<%
	for (Prefectures pref : prefList) {
		boolean isSelected = pref.getPrefId() == selectedPrefId;
	%>
		<option value="<%= pref.getPrefId() %>" <%= isSelected ? "selected" : "" %>><%= pref.getPrefName() %></option>
	<%
	}
	%>
</select><br><br>

<!--  市区町村の選択 -->
<select id="citySelect" name="cityId">
<option value="">市区町村を選択してください</option>
</select><br><br>

<!-- JSを呼び出す -->
<script src="<%= request.getContextPath() %>/js/address_select.js"></script>

<script>
	const cityMap = {};
	<%	for (Cities city : citiesList) { %>
		if (!cityMap[<%= city.getPrefId() %>]) {
			cityMap[<%= city.getPrefId() %>] = [];
		}
		cityMap[<%= city.getPrefId() %>].push({ id: <%= city.getCityId() %>, name: "<%= city.getCityName() %>" });
	<%
	}
	%>

	initializeAddress("<%= selectedPrefId %>", "<%= selectedCityId %>");
</script>

<!-- 電話番号 -->
<p>現在の電話番号: <%=request.getAttribute("user") != null ? ((Users) request.getAttribute("user")).getTel() : ""%></p>
<textarea name="tel"><%=request.getAttribute("user") !=null ? ((Users) request.getAttribute("user")).getTel() : ""%></textarea><br><br>

<!-- 変更ボタン -->
<button type="submit" name="button">変更する</button>
</form>
<!-- 戻るボタン -->
<form action="${pageContext.request.contextPath}/InfoSelect">
<button type="submit" name="button">戻る</button>

</body>
</html>