<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<!--  spring 파일업로드 설정
		1.commons-fileupload 의존 추가(pom.xml)	
		2. mutipartResolver 등록 (servlet-context.xml)
		
		client side
		<form method="post" enctype="mulitpart/form-data"/>
 -->
 
 <h2>spring part</h2>
 <form action="/mvc/fileupload" method="post" enctype="multipart/form-data">
 	<input type="text" name="userId" value="brown"/><br> 
 	<input type="file" name="profile"/> <br>
 	<input type="submit" value="전송">
 </form>
 
</body>
</html>	