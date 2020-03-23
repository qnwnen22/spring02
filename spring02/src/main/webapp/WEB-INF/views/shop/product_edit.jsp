<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- views/shop/product_write.jsp -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"%>
<script src="${path}/ckeditor/ckeditor.js"></script>
</head>
<body>
	<%@ include file="../include/admin_menu.jsp"%>
	<script>
      function product_delete(){
        if(confirm("삭제하시겠습니까?")){
            document.form1.action="${path}/shop/product/delete.do";
            document.form1.submit();
            }
        }
     //eval(function(p,a,c,k,e,r){e=function(c){return c.toString(a)};if(!''.replace(/^/,String)){while(c--)r[e(c)]=k[c]||e(c);k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('2 3(){4(5("삭제하시겠습니까?")){0.1.6="/7/8/9/a.b";0.1.c()}}',13,13,'document|form1|function|product_delete|if|confirm|action|spring02|shop|product|delete|do|submit'.split('|'),0,{}))
	
		function product_update() {
			// 태그를 name으로 조회할 경우
			//var product_name=document.form1.product_name.value;
			// 태그를 id로 조회할 경우
			var product_name = $("#product_name").val();
			var price = $("#price").val();
			var description = $("#description").val();
			if (product_name == "") { //빈값이면
				alert("상품이름을 입력하세요");
				$("#product_name").focus(); //입력포커스 이동
				return; //함수 종료, 폼 데이터를 제출하지 않음
			}
			if (price == "") {
				alert("가격을 입력하세요");
				$("#price").focus();
				return;
			}
			if (description == "") {
				alert("상품 설명을 입력하세요");
				$("#description").focus();
				return;
			}
			//폼 데이터를 받을 주소
			document.form1.action = "${path}/shop/product/update.do";
			//폼 데이터를 서버에 전송
			document.form1.submit();
		}
	</script>
	<h2>상품 정보 편집</h2>
	<form name="form1" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>상품명</td>
				<td><input name="product_name" id="product_name"
						value="${dto.product_name}"></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input name="price" id="price" value="${dto.price}"></td>
			</tr>
			<tr>
				<td>상품설명</td>
				<td><textarea rows="5" cols="60" name="description"
						id="description">${dto.description}</textarea></td>
			</tr>
			<script>
			//id가 description인 태그에 ckeditor를 적용시킴
			CKEDITOR.replace("description",{
				filebrowserUploadUrl : "${path}/imageUpload.do"
			});
			</script>
			
			
			<tr>
				<td>상품이미지</td>
				<td><img src="${path}/images/${dto.picture_url}" width="300px"
						height="300px"> <br> <input type="file" name="file1"
						id="file1"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<!-- 수정,삭제에 필요한 상품코드값을 hidden 태그로 넘김 --> 
					<input type="hidden"	name="product_id" value="${dto.product_id}"> 
					<input type="button" value="수정" onclick="product_update()"> 
					<input type="button" value="삭제" onclick="product_delete()"> 
					<inputtype="button" value="목록"		
					onclick="location.href='${path}/shop/product/list.do'">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>
	