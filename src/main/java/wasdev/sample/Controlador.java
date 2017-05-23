package wasdev.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wasdev.sample.rest.VisitorAPI;


@WebServlet(urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        try{
            response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        ServletContext aplicacion = this.getServletContext();
        HttpSession sesion = request.getSession();
        
        String vista = "/index.jsp";
        
        VisitorAPI API = new VisitorAPI();
        
        ArrayList<String> listaComentario = new ArrayList<String>();
        listaComentario = (ArrayList<String>) aplicacion.getAttribute("listaComentario");
        if(listaComentario == null)
        {
        	listaComentario = API.getVisitors();
        }
        
        String url_imagen = request.getParameter("url");
        String comentario = request.getParameter("comentario");
        String palabra = request.getParameter("palabra");
        
        if(url_imagen != null)
        {
        	String imagen = Imagen.analyze(url_imagen);
        	aplicacion.setAttribute("url", url_imagen);
        	aplicacion.setAttribute("imagen", imagen);
        }
        
        if(comentario !=null)
        {
        	Visitor visitor = new Visitor();
        	visitor.setName(comentario);
        	API.newToDo(visitor);
        	listaComentario = API.getVisitors();
        }
        
        
        
        if(listaComentario != null)
        {
            ArrayList<String> listaLikes = new ArrayList<String>();
            for(int i=0; i<listaComentario.size(); i++)
            {
                listaLikes.add(Sentimiento.analyze(listaComentario.get(i)));
            }
            aplicacion.setAttribute("listaComentario", listaComentario);
            aplicacion.setAttribute("listaLikes", listaLikes);
        }
        
        if(palabra != null)
        {
        	String traduccion = Traductor.translate(palabra);
        	aplicacion.setAttribute("palabra", palabra);
        	aplicacion.setAttribute("traduccion", traduccion);
        }
        
        
        RequestDispatcher rd = request.getRequestDispatcher(vista);
        rd.forward(request, response);
        
        }catch(Exception e){
            String vista = "/error.jsp";
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            request.setAttribute("error", exceptionAsString);
            RequestDispatcher rd = request.getRequestDispatcher(vista);
            rd.forward(request, response);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}