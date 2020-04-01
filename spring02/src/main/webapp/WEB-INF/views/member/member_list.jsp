<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>회원목록</h2>
<input type="button" value="회원등록"
	onclick="location.href='${path}/member/write.do'">
<table border="1" width="100%">
	<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>이메일</th>
		<th>우편번호</th>
		<th>도로명주소</th>
		<th>상세주소</th>
		<th>가입일자</th>
	</tr>
<c:forEach var="row" items="${list}"> 
	<tr>
		<td>${row.userid}</td>
		<td>
<a href="${path}/member/view.do?userid=${row.userid}">
${row.name}</a>
		</td>
		<td>${row.email}</td>
		<td>${row.addr1}</td>
		<td>${row.addr2}</td>
		<td>${row.addr3}</td>
		<td><fmt:formatDate value="${row.join_date}"
			pattern="yyyy-MM-dd HH:mm:ss" /> </td>						
	</tr>
</c:forEach>	
</table>
</body>
</html>
