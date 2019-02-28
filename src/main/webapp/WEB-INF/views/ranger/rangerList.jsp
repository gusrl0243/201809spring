<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2>rangerList.jsp</h2>
	
	<table>
		<thead>
			<tr>
				<td>이름</td>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${rangers }" var="ranger">
				<tr>
					<td>${ranger}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	게시판 구분 : ${boardGb }<br>
	게시판 구분 : ${boardGb2 }<br>
	session scope : ${sessionScope.boardGb2 }<br>
	
	
</body>
</html>