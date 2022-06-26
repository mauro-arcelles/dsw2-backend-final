package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Cliente;
import com.proyecto.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Override
	public List<Cliente> listaCliente() {
		return repository.findAll();
	}

	@Override
	public Cliente insertaActualizaCliente(Cliente cliente) {
		return repository.save(cliente);
	}

	@Override
	public List<Cliente> listaClienteNombreDniUbigeoEstado(String nombre, String dni, int idUbigeo, int estado) {
		return repository.listaClienteNombreDniUbigeoEstado(nombre, dni, idUbigeo, estado);
	}

	@Override
	public void eliminaCliente(int id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<Cliente> buscaCliente(int id) {
		return repository.findById(id);
	}

	@Override
	public List<Cliente> listaClientePorNombreLike(String nombre) {
		return repository.listaPorNombreLike(nombre);
	}

	@Override
	public Cliente porCorreo(String correo) {
		return repository.findByCorreo(correo);
	}

}
