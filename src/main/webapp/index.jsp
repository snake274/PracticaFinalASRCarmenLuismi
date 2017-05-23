
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Periódico ASR</title>
</head>
<body>
<div id="PaginaTotal">
<h1>Noticia de la semana</h1>
	<div id="imagen" class="imagen">
		<c:choose>
			<c:when test="${applicationScope.url != null}">
				<img src="${applicationScope.url}"/>
				<c:out value="${applicationScope.imagen}"/>
			</c:when>
			
			<c:otherwise>
				No hay ninguna imagen seleccionada
			</c:otherwise>	
		</c:choose>
		<br>
			Introduce la URL de una imagen: 
			<input type="text" name="url" id="TextoImagen">
			<input type="submit" value="Subir" onclick="getActionImagen();">
	</div>
	
	<div class="noticia">
		Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum
		Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum
	</div>
	
	<div id="comentarios" class="comentarios">
            <% ArrayList<String> listaComentario = (ArrayList<String>) application.getAttribute("listaComentario");
                ArrayList<String> listaLikes = (ArrayList<String>) application.getAttribute("listaLikes");
                if(listaComentario !=null){
                    for(int i=0; i<listaComentario.size(); i++)
                    { %>
	                <article>
	                             	<%out.println(listaComentario.get(i)); %><br/>
                                        <% if(listaLikes.get(i).equals("like"))
                                                out.println("Like");
                                        else
                                                out.println("Dislike");
                                        %>
					</article>
                    <%}
                }%>
			Introduce el comentario: 
			<input type="text" name="comentario" id="TextoComentario">
			<input type="submit" value="Publicar" onclick="getActionComentario();">

	     
	     
	</div>
	
	<div id="traductor" class="traductor">
		
			Introduce la palabra que quieras traducir: 
			<input type="text" name="palabra" id="TextoPalabra">
			<input type="submit" value="Subir" onclick="getActionTraductor();">

		</br>
		<c:choose>
		<c:when test="${applicationScope.traduccion != null}">
				La palabra intruducida es <c:out value="${applicationScope.palabra}"/> y su traducción es
				<c:out value="${applicationScope.traduccion}"/><br/>
		</c:when>
		</c:choose>
	</div>
</div>
<script src="js/funciones.js">
</script>
</body>
</html>