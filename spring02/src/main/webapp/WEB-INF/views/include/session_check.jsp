<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- views/include/session_check.jsp -->
<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.admin_userid == null }">
	<script>
		alert("로그인 하신 후 사용하세요.");
		location.href="${path}/admin/login.do";
	</script>
</c:if>
    