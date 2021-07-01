<%@page import="com.sm.news.domain.Pagination"%>
<%@page import="com.sm.news.domain.City"%>
<%@page import="com.sm.news.domain.Article"%>
<%@page import="java.util.List"%>
<%@page import="com.sm.news.service.NewsServiceImpl"%>
<%@page import="com.sm.news.service.NewsService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <%@ include file="./menu.jsp"%>
  <%
  NewsService ns = NewsServiceImpl.getInstance();
  Pagination paging = ns.getPageBean(majorcity_code, curPage);
  List<Article> list = ns.articleList(majorcity_code, paging);
  City selectCity = ns.selectCity(majorcity_code);
  session.setAttribute("list", list);
  session.setAttribute("paging", paging);
  session.setAttribute("selectCity", selectCity);
  %>
  <div class="container-fluid">
    <div class="jumbotron">
      <h1>TOTAL : ${selectCity.totalArticle }</h1>
      <p>since. 2021.06</p>
    </div>
    <!-- table -->
    <div class="row">
      <div class="col-md-12">
        <table class="table table-hover">
          <thead>
            <tr>
              <th style="text-align: center;">NO</th>
              <th width="70%" style="text-align: center;">제목</th>
              <th style="text-align: center;">날짜</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="article" items="${list }">
              <tr>
                <td style="text-align: center;">${article.article_id }</td>
                <td><a href="${article.article_url }" target="_blank">
                    ${article.article_title } </a></td>
                <td style="text-align: center;">${article.article_input_date }</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
    <!-- pagination -->
    <div class="row" align="center">
      <div class="col-md-12 center-block">
        <div class="pagination">
          <input type="hidden" name="page" value="${paging.currentPage}">
          <c:if test="${paging.startPage > paging.pagePerBlock }">
            <button
              onclick="location.href='./list.jsp?page=${paging.startPage }&city=${selectCity.majorcity_code}'"
              class="btn btn-default btn-sm">&laquo;</button>
          </c:if>
          <c:forEach var="i" begin="${paging.startPage }"
            end="${paging.endPage }">
            <c:if test="${paging.currentPage == i }">
              <button
                onclick="location.href='./list.jsp?page=${i }&city=${selectCity.majorcity_code}'"
                class="btn btn-primary btn-sm" disabled="disabled">${i }</button>
            </c:if>
            <c:if test="${paging.currentPage != i }">
              <button
                onclick="location.href='./list.jsp?page=${i }&city=${selectCity.majorcity_code}'"
                class="btn btn-default btn-sm">${i }</button>
            </c:if>
          </c:forEach>
          <c:if test="${paging.endPage < paging.totalPage }">
            <button
              onclick="location.href='./list.jsp?page=${paging.endPage }&city=${selectCity.majorcity_code}'"
              class="btn btn-default btn-sm">&raquo;</button>
          </c:if>
        </div>
      </div>
    </div>
  </div>
</body>
</html>