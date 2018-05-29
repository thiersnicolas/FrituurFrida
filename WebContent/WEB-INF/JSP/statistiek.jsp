<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name="title" value='Statistieken' />
</c:import>
</head>
<h1>Statistieken</h1>
<ul>
<c:forEach var="entry" items="${aantalrequestsmap}">
	<li>${entry.key}: ${entry.value} requests</li>
</c:forEach>
</ul>
<body>
</body>
</html>