import java.sql.*;
 /**
  * Clase GestionBD.
  * 
  * En esta clase se implementa todo lo necesario para realizar la conexion con 
  * la BD. 
  * 
  * @author Alex
  */
public class GestionBD {
    //Variables Grobales para el driver y la URL donde se encuentra la BD MySQL.
    public  String DRIVER_MYSQL = "com.mysql.jdbc.Driver"; 
    public  String URL_MYSQL = "jdbc:mysql://localhost:3306/usuarios";
    public  Connection conn;
    
    /*Constructor de la clase que realizara la conexion con la BD MySQL.*/
    public GestionBD(){
            cargarDriver();
    }
    
    /**
     * Metodo cargarDriver.
     * 
     * Carga el driver necesario para la BD MySQL.
     */
    private void cargarDriver() {
        try {
	     Class.forName(DRIVER_MYSQL);
	    }catch (ClassNotFoundException e) {
		System.err.println("Se ha producido un error durante la carga del Driver.");
		e.printStackTrace();
	}
    }
    
    /**
     * Metodo getConexion.
     * 
     * Realiza la conexion con la BD, siendo necesario introducir el usuario y la pass de la BD.
     */
    public void getConexion(){
	try {
		conn = DriverManager.getConnection(URL_MYSQL,"root","admin!6593");
		} catch (SQLException e) {
		System.err.println("Se ha producido un error en la conexion con la BD.");
		e.printStackTrace();
	} 	
    }
    
    /**
     * Metodo MostrarUsuarios.
     * 
     * Muestra los usuarios de la tabla clientes que hay introducidos en el momento de la consulta.
     * @throws SQLException 
     */
    public void MostrarUsuarios() throws SQLException{
        ResultSet rs = null;
	getConexion();
	    String sql="SELECT * FROM clientes";
	        try {
                   PreparedStatement stm = conn.prepareStatement(sql);
                   rs = stm.executeQuery();
                    while(rs.next()){//Leo mientras quede algo. 
                      System.out.println(rs.getString("idclientes"));
                      System.out.println(rs.getString("usuario"));
                      System.out.println(rs.getString("clave"));
                                    }
                    rs.close();
                    stm.close();
                    conn.close();
		    } catch (SQLException e) {
			e.printStackTrace();
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
    
    /**
     * Metodo BuscarUsuario. 
     * 
     * Busca en la BD un usuario en funcion de su cuenta de usuario y su clave asociada entregados en el cliente 
     * previamente y lo muestra por pantalla con System.
     * @param usuario
     * @param clave
     * @throws SQLException 
     */
    public void BuscarUsuario(String usuario, String clave) throws SQLException{
	ResultSet rs = null;
	String sql="SELECT * FROM clientes WHERE `usuario`= ? AND `clave`= ?";
	getConexion();
	        try {
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, usuario);
		stm.setString(2, clave);
		rs = stm.executeQuery();
                    while(rs.next()){//Leo mientras quede algo. 
                      System.out.println(rs.getInt("idclientes"));
                      System.out.println(rs.getString("usuario"));
                      System.out.println(rs.getString("clave"));
                                    }
                    rs.close();
                    stm.close();
                    conn.close();
		    } catch (SQLException e) {
			e.printStackTrace();
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
    
    /**
     * Metodo RegresarUsuario 
     * 
     * Busca en la BD un usuario en funcion de su cuenta de usuario y su clave asociada entregada en el cliente previamente.
     * Una vez lo obtiene de la BD MySQL creada para el servidor lee los valores y realza la comprobacion la cual enviara de
     * vuelta como resultado siendo true o false para validar el login si este usuario esta creado o no en la BD.
     * 
     * @param usuario
     * @param clave
     * @return
     * @throws SQLException 
     */
    public boolean RegresarUsuario(String usuario, String clave) throws SQLException{
	ResultSet rs = null;
        String usser ="";
        String key   ="";
        boolean comprobacion = false;
	String sql="SELECT * FROM clientes WHERE `usuario`= ? AND `clave`= ?";
	getConexion();
	        try {
            PreparedStatement stm = conn.prepareStatement(sql); 
                stm.setString(1, usuario);
                stm.setString(2, clave);
                rs = stm.executeQuery();
                while(rs.next()){//Leo mientras quede algo.
                    usser = rs.getString("usuario");
                    key   = rs.getString("clave");
                }
                rs.close();
                    conn.close();
                    if(usser.equals(usuario)&&key.equals(clave)){
                        comprobacion = true;
                        return comprobacion;
                    }else{
                        return comprobacion;
                    }

		    } catch (SQLException e) {
			e.printStackTrace();
		    } catch (Exception e) {
			e.printStackTrace();
		    }
                return comprobacion;
		}
    
}
