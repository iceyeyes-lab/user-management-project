<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>更新完了</title>
</head>
<body>

	<h2>ユーザー情報の更新を行いました。</h2>
	<p>ユーザー名： ${user.name}</p>
	<p>住所： ${user.address}</p>
	<p>電話番号： ${user.tel}</p>
	
	<form action="${ pageContext.request.getContextPath() }/InfoSelect" method="get">
	<button type="submit">一覧ページに戻る</button>
	</form>
	
	<!-- 更新した情報をもう一度修正するボタン -->
	<form action="<c:url value='/Update' />" method="get">
		<input type="hidden" name="userId" value="${user.userId}">
		<button type="submit">更新した情報を修正する</button>
	</form>

</body>
</html>