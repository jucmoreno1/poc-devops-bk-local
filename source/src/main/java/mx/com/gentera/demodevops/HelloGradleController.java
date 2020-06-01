/**
 * 
 */
package mx.com.gentera.demodevops;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.gentera.demodevops.bs.Calculos;
import mx.com.gentera.demodevops.dto.Formulario;

@RestController
@RequestMapping("/api")
public class HelloGradleController {

	@RequestMapping(value = "/v1", method = RequestMethod.GET)
    public ResponseEntity<String> helloGradle() {
        return new ResponseEntity<>("API de entrada", HttpStatus.OK);
    }

	@RequestMapping(value = "/v2", method = RequestMethod.GET)
    public ResponseEntity<String> byeGradle() {
        return new ResponseEntity<>("API de salida", HttpStatus.OK);
    }
	
	@RequestMapping(value = "/calcularEdad", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> calcularEdad(@RequestBody Formulario formulario) {
		//Objeto para ejecutar las operaciones de negocio
		final Calculos calculos = new Calculos();
		
		//Se realiza el calculo de la edad
		if(formulario.getFechaNacimiento() != null) {
			formulario.setEdad(calculos.calcularAnios(formulario.getFechaNacimiento()));
		} else {
			formulario.setEdad(0);
		}
		
		//Objeto para almacenar la respuesta
		String jsonResponse = "";
		
		//Se transforma en la salida
		// Creating Object of ObjectMapper define in Jakson Api 
        final ObjectMapper objectMapper = new ObjectMapper(); 
		
        try { 
            // get Oraganisation object as a json string 
            jsonResponse = objectMapper.writeValueAsString(formulario); 
        }
        catch (IOException ioException) { 
        	ioException.printStackTrace(); 
        }
        
		return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
	}
}
