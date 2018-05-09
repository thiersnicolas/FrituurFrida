<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html>
<head>
<c:import url="/WEB-INF/JSP/head.jsp">
	<c:param name="title" value="Sauzen"></c:param>
</c:import>
</head>
<script>
document.getElementbyId("verwijderform").onSubmit = function(){
	document.getElementById("verwijderknop").disabled = true;
}
</script>
<body>
	<h1>Sauzen</h1>
	<form method="post" id="sausVerwijderForm">
		<ul class='zebra'>
			<c:forEach var='saus' items='${sauzen}'>
				<li><input type="checkbox" name="verwijderen" value="${saus.id}">${saus.id}-${saus.naam}<img src='images/${saus.naam}.png'
					alt='${saus.naam}'> <c:forEach var='ingredient'
						items='${saus.ingredienten}' varStatus='status'>
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
		<input type="submit" value="Aangevinkte sauzen verwijderen" id="verwijderknop">
	</form>
</body>
</html>