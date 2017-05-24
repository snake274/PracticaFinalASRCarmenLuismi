
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
		</br>
                
                    <div id="imagen" class="imagen">
                        <c:choose>
                                <c:when test="${applicationScope.url != null}">
                                        <img src="${applicationScope.url}"/></br>
                                        <c:out value="${applicationScope.imagen}"/>
                                </c:when>

                                <c:otherwise>
                                        No hay ninguna imagen seleccionada
                                        </br>
                                </c:otherwise>	
                                
                        </c:choose>
                        </br>
                            Introduce la URL de una imagen: 
                            <input type="text" name="url" id="TextoImagen">
                            <input type="submit" value="Subir" onclick="getActionImagen();">
                        </br> 
                    </div>

                    <div class="noticia">
                            <br>
                            <b>La importancia de la manzana en la dieta</b>
                            <br><br>
                            Comer una manzana al día es bueno para nuestro organismo y nos previene de muchas enfermedades; sin embargo, la consulta con el profesional y los chequeos anuales no se pueden quitar de nuestras agendas.
                            <br><br>
                            Para llegar a esta conclusión se realizó un experimento en 8400 personas, de las cuales el 10% ingería una manzana a diario. Este fue el porcentaje también de pacientes que no debieron consumir casi ningún medicamento en el lapso de un año (periodo de duración de la investigación).
                            <br><br>
                            Según el Director del equipo, Matthew Davis, de la Facultad de Enfermería de la Universidad de Michigan, el consumo de manzanas no estuvo relacionado con otros marcadores como, por ejemplo, la cantidad de visitas anuales al médico.
                            <br><br>
                            Otras de las conclusiones sobre el consumo de esta fruta es que aquellos que ingerían una pieza de manzana al día eran menos propensos a fumar y tenían niveles más altos de educación e inteligencia. Consumir esta fruta puede ser una señal de un modo de vida más saludable. Esto se debe a que la manzana es una buena fuente de vitamina C, fibra soluble y flavonoides.
                            <br><br>
                            Puede tomarse como postre, en el desayuno, como snack a media mañana, cuando sentimos ansiedad o hambre, para antes de dormir o previo a hacer deporte. No aporta cantidades significativas de calorías o azúcares y se puede consumir a cualquier edad.
                            <br><br>
                            El secreto de la manzana y sus bondades está en sus sustancias fitoquímicas que, además de prevenir el cáncer, ayudan a reducir los niveles de azúcar y grasas en sangre. Por lo tanto, es aconsejable que los diabéticos y personas con colesterol consuman, al menos, dos manzanas al día. Esta fruta permite depurar y limpiar la sangre.
                            <br><br>
                            La manzana, a su vez, tiene ácidos como el tartárico y el málico, que ayudan a digerir mejor las grasas que consumimos. Por ello se aconseja comer esta fruta como postre después de una ingesta copiosa, ya sea al mediodía o por la noche. Además, no permite que la glucosa aumente rápidamente después de las comidas.
                            <br><br>
                            Es perfecta para las personas que están haciendo dieta o que sufren de algún trastorno de ansiedad por la comida. Esto es así porque la manzana sacia nuestro apetito y evita que comamos de más. Puedes ingerir la cantidad que quieras al día sin restricción, ya que te ayudará a bajar de peso y sentirte con más energía (pero que no sea lo único que comes en todo el día).
                            <br><br>
                            Por último, si estás dudando con el color, la manzana más saludable o beneficiosa, sin dudas, es la roja.
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
                        <br><br>
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
            <c:when test="${sessionScope.traduccion != null}">
                La palabra intruducida es <c:out value="${sessionScope.palabra}"/> y su traducción es
                <c:out value="${sessionScope.traduccion}"/><br/>
            </c:when>
            </c:choose>
        </div>
        </div>
        </div>
       
        <script src="js/funciones.js">
        </script>
    </body>
</html>
