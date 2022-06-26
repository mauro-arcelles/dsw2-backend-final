package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.entidad.Reclamo;
import com.proyecto.repository.ReclamoRepository;

@Service
public class ReclamoServiceImpl implements ReclamoService {
	
	@Autowired
	private ReclamoRepository repositorio;

	@Override
	public Reclamo insertaActualizaReclamo(Reclamo obj) {
		return repositorio.save(obj);
	}

	@Override
	public List<Reclamo> listaReclamo() {
		return repositorio.findAll();
	}
	
	@Override
	public List<Reclamo> listaReclamoParametros(String descripcion, int estado, String fecCom1, String fecCom2, int TipoReclamo, int Cliente){
		return repositorio.listaReclamoParametros(descripcion, estado, fecCom1, fecCom2, TipoReclamo, Cliente);
	}

	@Override
	public Reclamo InsertaActualizaReclamo(Reclamo obj) {
		return repositorio.save(obj);
	}

	@Override
	public List<Reclamo> listaReclamoPorDescripcion(String descripcion) {
		
		return repositorio.listaReclamoPorDescripcion(descripcion);
	}

	@Override
	public void EliminaReclamo(int id) {
		repositorio.deleteById(id);
		
	}

	@Override
	public Optional<Reclamo> buscaReclamo(int id) {
		return repositorio.findById(id);
	}
	
	
	
}
