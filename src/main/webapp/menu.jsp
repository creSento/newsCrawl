<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script
  src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
  <%
  String curPage = request.getParameter("page");
  String majorcity = request.getParameter("city");
  if (majorcity == null) {
      majorcity = "0";
  }
  int majorcity_code = Integer.parseInt(majorcity);
  session.setAttribute("city", majorcity_code);
  %>
  <nav class="navbar navbar-default">
    <ul class="nav nav-pills nav-justified">
      <c:choose>
        <c:when test="${city == 0 }">
          <li role="presentation" class="active">
        </c:when>
        <c:otherwise>
          <li role="presentation">
        </c:otherwise>
      </c:choose>
       <a href="index.jsp">HOME</a>
      </li>
      <c:choose>
        <c:when test="${city == 11 }">
          <li role="presentation" class="active">
        </c:when>
        <c:otherwise>
          <li role="presentation">
        </c:otherwise>
      </c:choose>
      <a href="list.jsp?city=11&page=1">서울</a>
      </li>
      <c:choose>
        <c:when test="${city == 23 }">
          <li role="presentation" class="active">
        </c:when>
        <c:otherwise>
          <li role="presentation">
        </c:otherwise>
      </c:choose>
      <a href="list.jsp?city=23&page=1">인천</a>
      </li>
      <c:choose>
        <c:when test="${city == 31 }">
          <li role="presentation" class="active">
        </c:when>
        <c:otherwise>
          <li role="presentation">
        </c:otherwise>
      </c:choose>
      <a href="list.jsp?city=31&page=1">경기</a>
      </li>
    </ul>
  </nav>
</body>
</html>