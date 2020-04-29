<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
function daumZipCode() {
	 new daum.Postcode({
	     oncomplete: function(data) {
	         var fullAddr = ''; // 최종 주소 변수
	         var extraAddr = ''; // 조합형 주소 변수
        
	         if (data.userSelectedType === 'R') { 
	             fullAddr = data.roadAddress;

	         } else { // 사용자가 지번 주소를 선택했을 경우(J)
	             fullAddr = data.jibunAddress;
	         }
	      // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
	         if(data.userSelectedType === 'R'){
	             //법정동명이 있을 경우 추가한다.
	             if(data.bname !== ''){
	                 extraAddr += data.bname;
	             }
	// 건물명이 있을 경우 추가한다.
	             if(data.buildingName !== ''){
	                 extraAddr += (extraAddr !== '' ? ', ' 
	+ data.buildingName : data.buildingName);
	             }
	// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
	             fullAddr += (extraAddr !== '' ? 
	            		 ' ('+ extraAddr +')' : '');
	         }

	         // 우편번호와 주소 정보를 해당 필드에 넣는다.
	         document.getElementById('zipcode').value 
	         	= data.zonecode; //5자리 새우편번호 사용
	         document.getElementById('address1').value 
	         	= fullAddr;

	         // 커서를 상세주소 필드로 이동한다.
	         document.getElementById('address2').focus();
	     }
	 }).open();
	}

$(function(){
	$("#btnUpdate").click(function(){
		document.form1.action="${path}/member/myupdate.do";
		document.form1.submit();
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>회원정보 수정</h2>
<form name="form1" method="post">
<table border="1" width="400px">
	<tr>
		<td>아이디</td>
		<td><input name="userid" value="${dto.userid}" readonly></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd"></td> 
	</tr>
	<tr>
		<td>이름</td>
		<td><input name="name" value="${dto.name}"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input name="email" value="${dto.email}"></td>
	</tr>
	
	<tr>
		<td>우편번호</td>
		<td><input name="addr1" id="zipcode" value="${dto.addr1}" readonly>
		<input type="button" onclick="daumZipCode()" 
		value="우편번호 찾기"><br>
		</td>
	</tr>
	<tr>
		<td>도로명주소</td>
		<td><input name="addr2" id="address1" value="${dto.addr2}"></td>
	</tr>
	<tr>
		<td>상세주소</td>
		<td><input name="addr3" id="address2" value="${dto.addr3}"></td>
	</tr>
	
	<tr>
		<td>회원가입일자</td>
		<td>
		<c:if test="${join_date != null }">
			<fmt:formatDate value="${join_date}" 
				pattern="yyyy-MM-dd HH:mm:ss" />
		</c:if>
		
		<fmt:formatDate value="${dto.join_date}" 
			pattern="yyyy-MM-dd HH:mm:ss" />
			
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="button" value="수정" id="btnUpdate">
			<div style="color: red;">${message}</div>	
		</td>
	</tr>
</table>	
</form>
</body>
</html>
	