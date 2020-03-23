<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<style>
.fileDrop {
	width: 100%;
	height: 200px;
	border: 1px dotted blue;
}
small {
	margin-left:3px;
	font-weight: bold;
	color: gray;
}
</style>
<script>
$(function(){
	//드래그 기본 효과를 막음 .은 클래스 class=fileDrop, #은 id
	$(".fileDrop").on("dragenter dragover", function(event){
		event.preventDefault();
	});
	$(".fileDrop").on("drop",function(event){
		//drop이 될 때 기본 효과를 막음
		event.preventDefault();
		//첨부파일 배열(여러개를 동시에 선택해서 드래그 할 수 도 있기때문에 한개만 처리하도록 조치)
		var files=event.originalEvent.dataTransfer.files;
		var file=files[0]; //첫번째 첨부파일
		var formData=new FormData(); //폼 객체
		formData.append("file",file); //폼에 file 변수 추가
		//서버에 파일 업로드(백그라운드에서 실행됨)
		//아래 contentType: false는  multipart/form-data로 처리되는것과 같음
		$.ajax({
			type: "post",
			url: "${path}/upload/uploadAjax",
			data: formData,
			dataType: "text",
			processData: false,
			contentType: false, 
			success: function(data,status,req){
				console.log("data:"+data); //업로드된 파일 이름
				console.log("status:"+status); //성공,실패 여부
				console.log("req:"+req.status);//요청코드값

				var str="";
				if(checkImageType(data)){//이미지 파일
					str="<div><a href='${path}/upload/displayFile?fileName="
						+getImageLink(data)+"'>";
				str+="<img src='${path}/upload/displayFile?fileName="
						+data+"'></a>"; 
					}else{//이미지가 아닌 경우
						str="<div>";
						str+="<a href='${path}/upload/displayFile?fileName="
								+data+"'>"+getOriginalName(data)+"</a>";
						}
				str+="<span data-src="+data+">[삭제]</span></div>";
				$(".uploadedList").append(str);
			}
		});
	});//fileDrop 함수

	//첨부파일 삭제 함수
	$(".uploadedList").on("click","span",function(event){//내부적으로 span태그가 클릭되면 삭제
		var that=$(this);//this는 현재 클릭한 태그, 즉 span태그
		$.ajax({
			url: "${path}/upload/deleteFile",
			type: "post",
			data: {//data: "fileName="+$(this).attr("data-src") 아래와 같음	
				fileName: $(this).attr("data-src")
			},
			dataType: "text",
			success: function(result){
				if(result=="deleted"){
					that.parent("div").remove();//파일삭제되면 행전체<div>를 삭제처리
					//that은 span태그를 의미하는데 그 부모인 감싸고 있는 div태그를 지운다는 뜻
				}
			}
		});
	});
	


	
	function getOriginalName(fileName){
		if(checkImageType(fileName)){ //이미지 파일이면 skip
			return;
		}
		var idx=fileName.indexOf("_")+1; //uuid를 제외한 파일이름만 뽑음
		return fileName.substr(idx);
	}
	function getImageLink(fileName){
		if(!checkImageType(fileName)){//이미지 파일이 아니면 skip
			return;
		}
		//2018/08/25/s_f53e623b-24db-42f9-b607-9c04528056a5_apple-bg.jpg
		var front=fileName.substr(0,12);//연월일 경로(0~11번째까지 자르고)
		var end=fileName.substr(14);// 14번째 문자열앞의 s_ 제거
		return front+end;
	}
	function checkImageType(fileName){
		var pattern=/jpg|png|jpeg/i; //정규표현식(i는 대소문자 무시)
		return fileName.match(pattern); //규칙에 맞으면 true가 리턴
	}
});



</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>Ajax File Upload</h2>
<!-- 파일을 업로드할 영역 -->
<div class="fileDrop"></div>
<!-- 업로드된 파일 목록을 출력할 영역 -->
<div class="uploadedList"></div>

</body>
</html>

