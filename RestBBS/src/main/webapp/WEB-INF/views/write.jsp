<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
	<title>글쓰기</title>
</head>
<body>
	<h1>
		글쓰기  
	</h1>
	<form id="frm" name="frm" action="/rest/" method="POST">
		제목 <input type="text" id="title" name="title" /> <br/>
		작성자 <input type="text" id="user_name" name="user_name" /> <br/>
		<textarea id="content" name="content" rows="10" cols="30"></textarea> <br/>
		<input type="submit" value="등록" />
	</form>
</body>
</html>
