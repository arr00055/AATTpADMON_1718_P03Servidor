
import java.io.Serializable;

public class Cliente implements Serializable{
	// Variables de Cliente.
	private String usuario;
	private String clave;

	// Constructor de la clase Cliente.
	public Cliente(String usuario, String clave){
		this.usuario = usuario;
		this.clave = clave;
	}	
 
	// Getters y Setters
	public String getUsuario() {
		return usuario;
	}
 
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getClave() {
		return clave;
	}
 
	public void setClave(String clave) {
		this.clave = clave;
	}
        
        public void setValue(String usuario, String clave){
		this.usuario=usuario;
		this.clave=clave;
	}
 
}
