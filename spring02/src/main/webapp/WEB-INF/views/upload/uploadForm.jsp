<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<style>
iframe {
	width: 800px;
	height: 200px;
	border: 1px;
	border-style: solid;
}
</style>
</head>
<body>
<%@ include file="../include/menu.jsp" %>

<!-- 파일 업로드를 위한 필수 속성
enctype="multipart/form-data" -->
<form action="${path}/upload/uploadForm"
method="post" enctype="multipart/form-data" target="iframe1" >
	<input type="file" name="file">
	<input type="submit" value="업로드">
</form>
<!-- iframe에 업로드 결과 출력 -->
<iframe name="iframe1"></iframe>

</body>

</html>

