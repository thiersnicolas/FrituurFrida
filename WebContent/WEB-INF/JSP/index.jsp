<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url="/WEB-INF/JSP/head.jsp">
	<c:param name="title" value="Frituur Frida"></c:param>
</c:import>
</head>
<h1>Frituur Frida</h1>
<h2>Vandaag zijn we</h2> 
<div>${openGesloten}</div>
<dl>
	<dt>Straat</dt><dd>${adres.straat}</dd>
	<dt>Nummer</dt><dd>${adres.huisNr}</dd>
	<dt>Postcode</dt><dd>${adres.gemeente.postcode}</dd>
	<dt>Gemeente</dt><dd>${adres.gemeente.naam}</dd>
</dl>
<body>
</body>
</html>