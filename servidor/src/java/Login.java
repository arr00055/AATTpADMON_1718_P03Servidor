import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Clase Login. 
 * 
 * Esta clase implementa el servlet y actua como servidor respondiendo a la peticion POST que realizara el cliente, 
 * tras una consulta a la Base de Datos creada con MySQL. 
 * 
 * @author Alejandro Romo Rivero. 
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
            //Obtengo el parametro usuario enviado al servlet desde el formulario POST.
            String Usuario=request.getParameter("usuario");
            //Obtengo el parametro clave enviado al servlet desde el formulario POST.
            String Clave=request.getParameter("clave");
            //Creo un objeto conec de tipo GestionBD el cual maneja la conexion y todo lo relacionado con la BD.
            GestionBD conec = new GestionBD();
            //conec.MostrarUsuarios(); //Metodo para probar el correcto funcionamiento y muestra todos los usuarios registrados en la BD. 
            
            /**
             * Variable que recibe la respuesta de la comprobacion a la BD, para saber si el usuario esta registrado o no 
             * con las credenciales entregadas desde el cliente al servidor para la comprobacion.
             */
            boolean vuelta = conec.RegresarUsuario(Usuario, Clave);
            //conec.BuscarUsuario(Usuario, Clave); //Metodo para probar que muestra correctamente por System el usuario solicitado a partir de las credenciales.
            /**
             * A partir de la respuesta realizada a la BD con las credenciales introducidas se comprueba si el usuario existe o no,
             * si el usuario existe se dara el saludo adecuadamente, y si no se avisara del error al cliente, para dar la respuesta
             * se envia el resultado a partir de la vista "/Practica03.jsp". Las opciones para el formulario han sido deshabilitadas 
             * tras comprobar su correcto funcionamiento. 
            */
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
