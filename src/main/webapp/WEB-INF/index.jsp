<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h2>index.jsp</h2>
   <div style="color: black">asdf </div><br>
   2019 0228 0925 Thu <br>
   <div>Spring MVC 수업. </div><br>
   dispatcher servlet url pattern -> web.xml<br>
   mvc:default-servlet-handler -> servlet-context.xml <br><br>
   <table border="1">
      <thead>
         <tr>
            <td>dispatcher servlet url pattern</td>
            <td>mvc:default-servlet-handler</td>
            <td>정적 자료. jquery</td>
            <td>JSP. index.jsp</td>
            <td>스프링 MVC. ranger/getRangers</td>
         </tr>
      </thead>   
      
      <tbody>
         <tr>
            <td>/*</td>
            <td>미적용</td>
            <td>X</td>
            <td>X</td>
            <td>X</td>
         </tr>
         <tr>
            <td>/*</td>
            <td>적용</td>
            <td>O</td>
            <td>text 출력</td>
            <td>text 출력</td>
         </tr>
         <tr>
            <td>/</td>
            <td>미적용</td>
            <td>X</td>
            <td>O</td>
            <td>O</td>
         </tr>
         <tr>
            <td>/</td>
            <td>적용</td>
            <td>O</td>
            <td>O</td>
            <td>O</td>
         </tr>
      </tbody>
   </table> <br>
   <div>web - text - servlet mapping 정리 참고.</div>
</body>
</html>