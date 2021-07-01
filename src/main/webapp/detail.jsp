<%@page import="com.sm.news.domain.City"%>
<%@page import="java.util.List"%>
<%@page import="com.sm.news.service.NewsServiceImpl"%>
<%@page import="com.sm.news.service.NewsService"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
String state = request.getParameter("state");
String stateName = "";
if (state.equals("11")) {
    stateName = "서울";
} else if (state.equals("23")) {
    stateName = "인천";
} else if (state.equals("31")) {
    stateName = "경기";
}
NewsService ns = NewsServiceImpl.getInstance();
List<City> minorList = ns.totalMinor(Integer.parseInt(state));
session.setAttribute("minorList", minorList);
%>
<script type="text/javascript">
$(function () {
  
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
      
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          [{type: 'string', label: 'Resion', role: 'annotation'},  {type: 'number', label: 'articles'}]
        ]);
      <c:forEach var="city" items="${minorList }">
        data.addRow(["${city.minorcity_name}", parseInt("${city.totalArticle}")]);
      </c:forEach>
		
      var options = {
          title: '<%=stateName%>' + '(5% 이상)',
          width : 900,
          height : 600,
          colors : [ '#6ccde0', '#b98ff3', '#64d497', 
            '#3a9db1', '#9466d4', '#46c57f', 
            '#247f91', '#7749b8', '#29aa63' ],
      };

    var chart = new google.visualization.PieChart(document
        .getElementById('piechart'));

    chart.draw(data, options);
  }
  
      
});
</script>
</head>
<body>
    <div id="piechart"></div>
</body>
</html>