package com.bolsadeideas.springboot.di.app.models.domain;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

//RequestScope hace que dure lo que dura una petición HTTP de usuario
//Si Cliente es singleton, osea que dura todo lo que dura la aplicación
//Lo que sucede es que las variables no cambian y se concatenan los nombres que pusimos

//Session Scope para trabajar con sesiones (carro de compras etc) Cierra cuando cerramos el navegador/timeout/invalidacion de sesiojn
//Application Scope: se guarda en el servlet context (es parecido al singleton) y no en el application context de spring
//EN una aplicacion web se podria tener mas de 1servlet context por cada aplicacion web, por defecto es 1
//Si quiero guardar algo en la sesion HTTP debo utilizar la interfaz serializable, porque convierte el objeto en secuencia de bytes para guardar y transportar (por ejemplo jpa)
//Prototype para standalone (mas de una instancia de el mismo beans)

@Component
@RequestScope
public class Factura implements Serializable {

	/**
	 * pone un warning que necesita generar un identificador de serializador
	 */
	private static final long serialVersionUID = 946004357128146951L;

	@Value("${factura.descripcion}")
	private String descripcion;

	@Autowired
	private Cliente cliente;

	// No puedo anotar con component un item, asi que lo voy a tener que relacionar
	// con un Configuration
	
	@Autowired
	//@Qualifier("itemsFacturaOficina")
	private List<ItemFactura> items;
	
	//El postconstruct hace que luego de crear el elemento se ejecute esto y luego de haber inyectado la dependencia
	@PostConstruct
	public void inicializar() {
		cliente.setNombre(cliente.getNombre().concat(" ").concat("Matías"));
		descripcion = descripcion.concat(" del cliente: ".concat(cliente.getNombre()));
	}
	
	//Antes de destruir el objeto
	//Los objetos son de tipo singleton (una sola instancia)
	//Ejemplo si tengo conexiones de recursos puedo cerrarlos
	//El PreDestroy no sirve en sessionscope
	@PreDestroy
	public void destruir() {
		System.out.println("Factura destruida. ".concat(descripcion));
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}

}
