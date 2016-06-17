<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="./include/header.jsp" %>

<h1>
	게시글 목록  
</h1>
<table class="table table-striped">
	<thead>
		<th>no</th>
		<th>title</th>
		<th>username</th>
		<th>regdate</th>
		<th>view_cnt</th>
	</thead>
	<tbody>
	<c:forEach items="${list}" var="list">
		<tr>
			<td>${list.getBoard_no()}</td>
			<td><a href="/rest/${list.getBoard_no()}">${list.getTitle()}<span style="color:red;">[${list.getReply_cnt()}]</span></a></td>
			<td>${list.getUser_name()}</td>
			<td>${list.getRegdate()}</td>
			<td>${list.getView_cnt()}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<button type="button" id="btn" name="btn" class="btn btn-primary" onclick="goWrite()">글쓰기</button>
<script>
	function goWrite(){
		location.href = "/rest/new";
	}
</script>

<%@ include file="./include/footer.jsp" %>
