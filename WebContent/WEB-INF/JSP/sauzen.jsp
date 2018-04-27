<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html>
<head>
<c:import url="/WEB-INF/JSP/head.jsp">
	<c:param name="title" value="Frituur Frida"></c:param>
</c:import>
</head>
<body>
<h1>Sauzen</h1>
<ul class='zebra'>
		<c:forEach var='saus' items='${sauzen}'>
			<li>${saus.naam}: 
			<img src='images/${saus.naam}.png' alt='${saus.naam}'>
				<c:forEach var='ingredient' items='${saus.ingredienten}' varStatus='status'>
					<c:if test='${status.first}'>
						${ingredient}
					</c:if>
					<c:if test='${not status.first}'>
						, ${ingredient}
					</c:if>				
				</c:forEach>
				
			</li>
		</c:forEach>
	</ul>
</body>
</html>