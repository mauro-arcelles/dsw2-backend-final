package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Marca;
import com.proyecto.repository.MarcaRepository;

@Service
public  class MarcaServiceImpl implements MarcaService {

	@Autowired
	private MarcaRepository Repository;
	
	@Override
	public Marca insertaActualizaMarca(Marca obj) {
		return Repository.save(obj);
	}

	
	@Override
	public List<Marca> listaMarcaPorNombreCertificadoPaisEstado(String nombre, String certificado, int idPais, int estado,String fecInicio, String fecFin) {
		return Repository.listaMarcaPorNombreCertificadoPaisEstado(nombre, certificado, idPais, estado,fecInicio,  fecFin);
	}



	@Override
	public List<Marca> listaMarca() {
		return Repository.findAll();
	}


	@Override
	public List<Marca> listaMarcaPorNombreLike(String nombre) {
		return Repository.listaPorNombreMarcaLike(nombre);
	}


	@Override
	public void eliminaMarca(int idMarca) {
			Repository.deleteById(idMarca);
		
	}


	@Override
	public Optional<Marca> buscarMarca(int idMarca) {
		return Repository.findById(idMarca);
	}



	




}
