package com.bolsadeideas.springboot.di.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bolsadeideas.springboot.di.app.models.service.IServicio;


@Controller
public class IndexController {
	
	//lo inyectamos con autowired, solo significa eso, busca el tipo MiServicio y lo inyecta
	//para desacoplar totalmente private MiServicio servicio; tengo que usar una interfaz generica
	//asi si algun dia cambio el tipo de objeto/clase no depende de mi codigo y queda encapsulado.
	//LO hago desde Interfaz
	
	
	//Si tengo mas de una clase que usa IServicio de interfaz tengo que especificar sino uso @Autowired
	//Con @Qualifier le digo cual quiero (por ejemplo si no es el primario), puedo usar Qualifier sin Primary
	//Conviene cambiar los nombres en las clases para no tocar esta parte y pueda ser transparente
	@Autowired
	@Qualifier("miServicioPrincipal")
	private IServicio servicio;
	
	/*
	//Cuando lo hago por constructor puedo omitir el autowired, sino si uso el setter le tengo que poner
	@Autowired
	public IndexController(IServicio servicio) {

		this.servicio = servicio;
	}*/



	@GetMapping({"/","","/index"})
	public String index(Model model) {
		
		model.addAttribute("objeto", servicio.operacion());
		
		return "index";
	}


	
}
