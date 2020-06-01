/**
 * 
 */
package mx.com.gentera.demodevops.dto;

/**
 * @author e-marreyes
 *
 */
public class Formulario implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4396772135655358081L;
	
	private java.lang.String	nombre;
	private java.util.Date		fechaNacimiento;
	private java.lang.Integer	edad;
	
	public Formulario() {
		
	}

	/**
	 * @return the nombre
	 */
	public java.lang.String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(java.lang.String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public java.util.Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(java.util.Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the edad
	 */
	public java.lang.Integer getEdad() {
		return edad;
	}

	/**
	 * @param edad the edad to set
	 */
	public void setEdad(java.lang.Integer edad) {
		this.edad = edad;
	}
	
}
