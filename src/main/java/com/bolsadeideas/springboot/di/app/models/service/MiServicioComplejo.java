package com.bolsadeideas.springboot.di.app.models.service;

import org.springframework.stereotype.Component;

//@Component hace que sea una componente de spring y podamos inyectarlo para desacoplar
//Puedo usar @Service, es lo mismo solamente indica que es una fachada de logica de negocio

@Component("miServicioPrincipal")

public class MiServicioComplejo implements IServicio {
	
	
	//NOTA: SI TENEMOS CONSTRUCTOR TENEMOS QUE ADEMAS TENER UN CONSTRUCTOR VACIO, YA QUE SPRING LOS MANEJA AUTOMATICAMENTE CON EL CONSTRUCTOR SIN PARAMETROS

	@Override
	public String operacion() {
		return "ejecutando algún proceso importante complicado...";
	}
}
