package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import ch.qos.logback.core.net.server.Client;
import com.proyecto.entidad.Cliente;

public interface ClienteService{

	public abstract List<Cliente> listaCliente();

	public abstract Cliente insertaActualizaCliente(Cliente cliente);

	public abstract List<Cliente> listaClienteNombreDniUbigeoEstado(String nombre, String dni, int idUbigeo, int estado);

	public abstract void eliminaCliente(int id);

	public abstract Optional<Cliente> buscaCliente(int id);

	public abstract List<Cliente> listaClientePorNombreLike(String nombre);

	public abstract Cliente porCorreo(String correo);
	

}
