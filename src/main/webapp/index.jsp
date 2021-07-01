<%@page import="com.sm.news.domain.City"%>
<%@page import="com.sm.news.service.NewsServiceImpl"%>
<%@page import="com.sm.news.service.NewsService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
NewsService ns = NewsServiceImpl.getInstance();
City seoul = ns.totalMajor(11);
City incheon = ns.totalMajor(23);
City gyeonggi = ns.totalMajor(31);
%>
<style type="text/css">
  #piechart, #detail text {
    fill : #000; 
  }
</style>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {
    
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          [{type: 'string', label: 'Resion'},  {type: 'number', label: 'articles'}, {type: 'string', label: 'code'}],
          ['서울', <%=seoul.getTotalArticle()%>, '<%=seoul.getMajorcity_code()%>'],
          ['인천', <%=incheon.getTotalArticle()%>, '<%=incheon.getMajorcity_code()%>'],
          ['경기', <%=gyeonggi.getTotalArticle()%>, '<%=gyeonggi.getMajorcity_code()%>']
        ]);

        var options = {
          title: '수도권 뉴스기사 등록 현황',
          width: 900,
          height: 600,
          colors: ['#3a9db1', '#9466d4', '#46c57f']
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        chart.draw(data, options);
        
        google.visualization.events.addListener(chart, 'select', selectHandler);
        function selectHandler(e) {
          var selection = chart.getSelection();
          for (var i = 0; i < selection.length; i++) {
            var item = selection[i];
            var majorcity_code = data.getFormattedValue(item.row, 2);
            $('#detail').load('./detail.jsp?state=' + majorcity_code);
          }
        }
      }
      
      $('#totalArea').click(function() {
        $('#piechart').load('index.jsp');
      })
    });
</script>
</head>
<body>
  <%@include file="./menu.jsp"%>
  <div class="container-fluid" align="center">
    <div class="row">
      <div class="col-md-12">
         <button class="btn btn-sm btn-default" id="totalArea">전체보기</button>
      </div>
      <div class="col-md-12">
        <div id="piechart"></div>
        <div id="detail"></div>
      </div>
    </div>
  </div>
</body>
</html>