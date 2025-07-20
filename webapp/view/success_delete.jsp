<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>削除成功</title>
</head>
<body>
	<h2>ユーザー情報を削除しました。</h2>
	<form action="<%= request.getContextPath() %>/InfoSelect" method="get">
	<button type="submit">一覧ページに戻る</button>
	</form>

</body>
</html>