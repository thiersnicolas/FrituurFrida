<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html>
<head>
<c:import url="/WEB-INF/JSP/head.jsp">
	<c:param name="title" value="Voorkeursauzen"></c:param>
</c:import>
</head>
<body>
	<h1>Voorkeursauzen</h1>
	<form>
		<label>Ingrediënt:<span>${fouten}</span><input type='text'
			name='ingredient' value='${ingredient}'
			placeholder="Typ hier het ingredient" /></label> <input type='submit'
			value='Toon de sauzen'>
	</form>

	<c:if test='${not empty sauzenMetIngredient}'>
		<ul class='zebra'>
			<c:forEach var='saus' items='${sauzenMetIngredient}'>
				<li><c:out value='${saus.naam}' /> ${pizza.prijs}&euro;</li>
			</c:forEach>
		</ul>
	</c:if>
	<c:if test='${empty sauzenMetIngredient and empty fouten}'>
		<div class='fout'>Geen sauzen gevonden</div>
	</c:if>
</body>
</html>