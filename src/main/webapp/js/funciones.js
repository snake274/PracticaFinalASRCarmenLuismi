function peticionAJAX(url, idDiv)
{
    var xmlHttpReq = new XMLHttpRequest();
	xmlHttpReq.onreadystatechange=function()
{
                if (xmlHttpReq.readyState == 4 && xmlHttpReq.status==200)
                    document.getElementById(idDiv).innerHTML = xmlHttpReq.responseText;
}

	xmlHttpReq.open('GET', url, true); 
	xmlHttpReq.send();
}

function getActionComentario()
{
	var comentario = document.getElementById("TextoComentario").value;
	var url = "Controlador?comentario=" + comentario;
	//form.action = document.getElementById('form_comentario').action; //Will retrieve it
	peticionAJAX(url, "PaginaTotal");

}

function getActionImagen()
{
	var urlImagen = document.getElementById("TextoImagen").value;
	var url = "Controlador?url=" + urlImagen;
	//form.action = document.getElementById('form_imagen').action; //Will retrieve it
	peticionAJAX(url, "PaginaTotal");

}

function getActionTraductor()
{
	var palabra = document.getElementById("TextoPalabra").value;
	var url = "Controlador?palabra=" + palabra;
	//form.action = document.getElementById('form_traductor').action; //Will retrieve it
	peticionAJAX(url, "PaginaTotal");

}

window.onload=function()
{
	peticionAJAX("Controlador","PaginaTotal");
}