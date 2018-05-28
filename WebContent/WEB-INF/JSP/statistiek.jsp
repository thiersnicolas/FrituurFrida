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
<h2>aantal requests statistiek: ${aantalRequests}</h2>
<body>
</body>
</html>