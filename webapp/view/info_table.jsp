<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entity.Users" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メインテーブル</title>
</head>
<body>
<h1>新規登録は下部のボタンをクリック</h1>
<%
List<Users> userList = (List<Users>) request.getAttribute("userList");
%>
<table border="1">
	<tr>
		<th>ID</th>
		<th>名前</th>
		<th>住所</th>
		<th>電話番号</th>
		<th></th><!-- ボタン格納用 -->
	</tr>
	<%
	for(Users user: userList) {
	%>
	 <tr>
	 	<td><%= user.getUserId() %></td>
	 	<td><%= user.getName() %></td>
	 	<td><%= user.getAddress() %></td>
	 	<td><%= user.getTel() %></td>
	 	<td>
	 		<!-- 変更ボタン -->
			<form action="<%= request.getContextPath() %>/Update" method="get" style="display:inline;">
			<input type="hidden" name="userId" value="<%= user.getUserId() %>">
			<button type="submit">変更</button>
			</form>
		
			<!-- 削除ボタン -->
			<form action="<%= request.getContextPath() %>/Delete" method="get" style="display:inline;">
			<input type="hidden" name="userId" value="<%= user.getUserId() %>">
			<button type="submit">削除</button>
			</form>
		</td>
	 </tr>
	<% } %>
</table><br>

	<!-- 新規登録フォームへ -->
	<form action="<%= request.getContextPath() %>/Form" method="get">
	<button type="submit" name="action">新規登録はこちら</button>
	</form>

</body>
</html>