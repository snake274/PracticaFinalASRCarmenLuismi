
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="css/estilo.css"/>
        
        
    </head>
    <body>
    <div id="PaginaTotal" class="textoIzquierdo">
    <title>Periódico ASR Carmen y Luismi</title>
        <div id="container" class="contenedor">
            <div id="superior" class="superior">
                <div id="cabecera" class="cabecera">
                    Periódico Comillas
                </div>
            </div>
            <hr class="lineaGorda">
            <div id="tiempo" class="tiempo">
                <%= new java.util.Date().toString() %>
            </div>
            <hr class="lineaGorda">
            <div id="principal" class="panelIzquierdo">
		<div id="tituloNoticia" class="tituloNoticia">
                    Noticia de la semana
		</div>
                
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
                        </br> 
                    </div>

                    <div class="noticia">
                            Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum
                            Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum
                    </div>
                    
                    <div class="textoFinal">
			Autores: Carmen Garcia Diaz y Luis Miguel Calzada Lorente
                    </div>
                    
                    <hr class="LineaFina">

                    <div id="comentarios" class="comentarios">
			<div id="tituloComentarios" class="tituloComentarios">
                            Comenta esta noticia
			</div>
			<br>
			<br>
                        <div id="comentarioUnico" class="comentarioUnico">
                        <% ArrayList<String> listaComentario = (ArrayList<String>) application.getAttribute("listaComentario");
                            ArrayList<String> listaLikes = (ArrayList<String>) application.getAttribute("listaLikes");
                            if(listaComentario !=null){
                                for(int i=0; i<listaComentario.size(); i++)
                                { %>
                                </br>
                                </br>
                                <article>
                                        <%out.println(listaComentario.get(i)); %>
                                            <% if(listaLikes.get(i).equals("like")){%>
                                                <img src="images/like.png"/>
                                            <%}else{%>
                                                <img src="images/dislike.png"/>
                                            <%}%>
                                    </article>
                                <%}%>
                        </div>
                            <%}%>
                        Introduce el comentario: 
                        <input type="text" name="comentario" id="TextoComentario">
                        <input type="submit" value="Publicar" onclick="getActionComentario();">
                    </div>
                </div>
            
                        
        <div id="traductor" class="traductor">
            Introduce la palabra que quieras traducir: 
            <input type="text" name="palabra" id="TextoPalabra">
            <input type="submit" value="Subir" onclick="getActionTraductor();">
            <br>
            <c:choose>
            <c:when test="${applicationScope.traduccion != null}">
                La palabra intruducida es <c:out value="${applicationScope.palabra}"/> y su traducción es
                <c:out value="${applicationScope.traduccion}"/><br/>
            </c:when>
            </c:choose>
        </div>
        </div>
        </div>
       
        <script src="js/funciones.js">
        </script>
    </body>
</html>
