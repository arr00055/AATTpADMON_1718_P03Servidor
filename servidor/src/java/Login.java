import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Alejandro Romo
 */
public class Login extends HttpServlet {

    /**
     * Metodo para manejar las peticiones HTTP <code>GET</code>.
     *
     * @param request servlet request.
     * @param response servlet response.
     * @throws ServletException si ocurre alguna excepcion.
     * @throws IOException si ocurre un error de I/O.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    /**
     * Metodo para manejar las peticiones HTTP <code>POST</code>.
     *
     * @param request servlet request.
     * @param response servlet response.
     * @throws ServletException si ocurre alguna excepcion.
     * @throws IOException si ocurre un error de I/O.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  

        try {
            //PrintWriter out = response.getWriter();
            //Obtengo el parametro usuario enviado al servlet desde el formulario POST.
            String Usuario=request.getParameter("usuario");
            //Obtengo el parametro clave enviado al servlet desde el formulario POST.
            String Clave=request.getParameter("clave");
            GestionBD conec = new GestionBD();
            //conec.MostrarUsuarios();
            
            boolean vuelta = conec.RegresarUsuario(Usuario, Clave);
            conec.BuscarUsuario(Usuario, Clave);
            if (vuelta==true){
                //out.println("Bienvenido "+Usuario+" con clave "+Clave);
                String result="OK, bienvenido "+Usuario+" con clave "+Clave;
                request.setAttribute("result", result);
                //request.setAttribute("Usuario", Usuario);
                //request.setAttribute("Clave", Clave);
                String url = "/Practica03.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }else{
                //out.println("<font color='red'><b>\"El usuario "+Usuario+" es incorrecto y/o ha introducido mal su clave de acceso, vuelva a intentarlo.\"</b></font>");
                //RequestDispatcher rd = request.getRequestDispatcher("index.html");
                String result="ERROR, el usuario "+Usuario+" no se encuentra registrado en el sistema.";
                request.setAttribute("result", result);
                //request.setAttribute("Usuario", Usuario);
                //request.setAttribute("Clave", Clave);
                String url = "/Practica03.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

}
