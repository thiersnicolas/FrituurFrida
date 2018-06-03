<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<c:import url='/WEB-INF/JSP/head.jsp'>
	<c:param name='title' value='Saus raden' />
</c:import>
</head>
<body>
	<h1>Tekst raden</h1>

		<c:if test='${not empty geraden and not gewonnen and not verloren}'>
			<h2>
				Te raden tekst:
				<c:forEach var='letter' items='${geraden}'>${letter}</c:forEach>
			</h2>
			<h2>letter:</h2>
			<form method="post">
			<input type="text" size="2" required  maxlength="1" name="raden">
			<input type="submit" value="Raden">
			</form>
		</c:if>
		<c:if test='${gewonnen}'>
			<h2>U bent gewonnen, ${saus}</h2>
			<img src='<c:url value="/images/gewonnen.png"/>' alt='LOVE'>
		</c:if>
		<c:if test='${verloren}'>
			<h2>U bent verloren, ${saus}</h2>
		</c:if>
		<form method="post">
		<button type="submit" name="nieuwSpel">Nieuw spel</button>
		</form>
	<img src='<c:url value="/images/${aantalFout}.png"/>' alt='The Hangman'>

</body>
</html>