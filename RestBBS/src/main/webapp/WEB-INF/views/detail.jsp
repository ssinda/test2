<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="./include/header.jsp" %>

<h1> 제목 : ${vo.title} </h1>

<table class="table table-striped">
	<tr>
		<td>글쓴이</td>
		<td>${vo.user_name}</td>
	</tr>
	<tr>
		<td>작성일</td>
		<td>${vo.regdate}</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${vo.content}</td>
	</tr>
</table>

<form action="/rest/${vo.board_no}" method="POST">
	<input type="hidden" id="_method" name="_method" value="delete" />
	<div>
		<input type="submit" class="btn btn-warning" value="삭제" style="float:right"/>
		<input type="button" id="list" class="btn btn-info" onclick="goList()" value="목록" style="float:right; margin-right:5px;"/>
	</div>
</form>

<br/>
	
<h3>댓글</h3>

<div id="reply_list"></div>
<div id="reply_page"></div>

<form id="reply">
	<input type="text" id="content" name="content" size="50" />
	, 작성자 : <input type="text" id="user_name" name="user_name" size="10" />
	<input type="button" id="btn" class="btn btn-primary" value="등록" onclick="insertReply()"/>
</form>

<script type="text/javascript">
	var currentPage = 1;

	function goList(){
		location.href = "/rest";
	}

	function setReplyList(data){
		var result = "<ul>";
		
		$(data).each(function(){
			result += "<li>" 
			+ this.user_name 
			+ ":" 
			+ this.content
			+ "<input type='button' id='btn_del' name='btn_del' "
			+ "class='btn btn-danger' onclick='deleteReply(" + this.reply_no + ")'"
			+ "value='삭제' />"
			+ "</li>";
		});
		
		result += "</ul>";
		
		document.getElementById("reply_list").innerHTML = result;
	}
	setReplyList();
	
	function getReplyList(page){
		
		if(page == null){
			page = currentPage;
		}
		
		$.ajax({
			type : 'get',
			url : '/reply/${vo.board_no}/' + page,
			headers : {
				"Content-Type" : "application/json",
				//"X-HTTP-Method-Override" : "GET",  ----  POST 이거나 GET인경우는 생략가능
			},
			dataType : 'json',
			data : '',
			success : function(result){
				setReplyList(result.l);
				setPagePrint(result.p)
			}
		});
		currentPage = page;
	}
	
	
	getReplyList(currentPage);
	
	function setPagePrint(pm){
		var str = "<ul class='pagination'>";
		if(currentPage > pm.endPage && currentPage > 1){
			getReplyList(currentPage - 1);
		}
		
		if(pm.prev){
			str += "<li> <a onclick='getReplyList("+(pm.startPage-1)+")'>&lt;</a> </li>"
		}
		
		for(var i = pm.startPage; i <= pm.endPage ; i++){
			if(i == pm.criteria.page){
				str += "<li class='active'><a href='#'>" + i + "</a></li>"
			}else{
				str += "<li><a onclick='getReplyList("+i+")' style='cursor:hand'>" + i + "</a></li>"
			}
		}
		
		if(pm.next){
			str += "<li> <a onclick='getReplyList("+(pm.endPage+1)+")'>&gt;</a> </li>"
		}
		
		str += "</ul>"
		document.getElementById("reply_page").innerHTML = str;
	}
		
	function insertReply(){
		
		var reply_content = $("#content").val();
		var reply_user = $("#user_name").val();
		
		$.ajax({
			type : 'post',
			url : '/reply/${vo.board_no}',
			headers : { 
				"Content-Type" : "application/json", 
				"X-HTTP-Method-Override" : "POST"
				},
			data : JSON.stringify({
				"content" : reply_content, 
				"user_name" : reply_user
				}),
			dataType : 'text',
			success : function(result){
				if(result == "SUCCESS"){
					getReplyList(currentPage);
				}
			}
		});
		$("#reply").get(0).reset();
	}
	
	function deleteReply(reply_no){
		
		$.ajax({
			type : 'delete',
			url : '/reply/' + reply_no,
			headers : { 
				"Content-Type" : "application/json"
				},
			data : '',
			dataType : 'text',
			success : function(result){
				if(result == "SUCCESS"){
					getReplyList(currentPage);
				}
			}
		});
		
	}

</script>

<%@ include file="./include/footer.jsp" %>
