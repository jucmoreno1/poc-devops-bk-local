/**
 * 
 */
package mx.com.gentera.demodevops.bs;

import java.util.Calendar;

/**
 * @author e-marreyes
 *
 */
public class Calculos {
	public int calcularAnios(java.util.Date fecha) {
		//Objeto calendar general
		Calendar fechaNacimiento = Calendar.getInstance();
	    
		//Se crea un objeto con la fecha actual
	    Calendar fechaActual = Calendar.getInstance();
	    
	    //Se asigna la fecha recibida a la fecha de nacimiento.
	    fechaNacimiento.setTime(fecha);
	    
	    //Se restan la fecha actual y la fecha de nacimiento
	    int anio = fechaActual.get(Calendar.YEAR)- fechaNacimiento.get(Calendar.YEAR);
	    int mes =fechaActual.get(Calendar.MONTH)- fechaNacimiento.get(Calendar.MONTH);
	    int dia = fechaActual.get(Calendar.DATE)- fechaNacimiento.get(Calendar.DATE);
	    
	    //Se ajusta el anio dependiendo el mes y el dia
	    if(mes<0 || (mes==0 && dia<0)){
	    	anio--;
	    }
	    
	    //Regresa la edad en base a la fecha de nacimiento
	    return anio;
	}
}
