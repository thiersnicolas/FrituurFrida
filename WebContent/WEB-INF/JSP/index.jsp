<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<fmt:setBundle basename="resourceBundles.teksten"/>
<fmt:setLocale value="${language}"/>
<!doctype html>
<html lang='nl'>
<head>
<c:import url="/WEB-INF/JSP/head.jsp">
	<c:param name="title" value="Frituur Frida"></c:param>
</c:import>
</head>
<fmt:parseDate value="${nu}" pattern='yyyy-MM-dd' var='nuAlsDate'
	type='date'/>
<body>
	<h1>Frituur Frida</h1>
	<h2><fmt:message key='vandaag'/>, <fmt:formatDate value='${nuAlsDate}' type='date' dateStyle='full'/>, <fmt:message key='zijn'/>:</h2>
	
	<div><img src= 'images/<fmt:message key='openGesloten'><fmt:param value='${openGesloten}'/></fmt:message>.png' alt='<fmt:message key='openGesloten'/>'></div>
	<dl>
		<dt><fmt:message key='straat'/></dt>
		<dd>${adres.straat}</dd>
		<dt><fmt:message key='nummer'/></dt>
		<dd>${adres.huisNr}</dd>
		<dt><fmt:message key='postcode'></fmt:message></dt>
		<dd>${adres.gemeente.postcode}</dd>
		<dt><fmt:message key='gemeente'/></dt>
		<dd>${adres.gemeente.naam}</dd>
	</dl>
	<footer><fmt:message key='helpdesk'/>${helpdesk}</footer>
</body>
</html>