package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.Marca;

public interface MarcaService {
	
	public  abstract List<Marca> listaMarca();
	
	//Para la consulta
	public abstract List<Marca> listaMarcaPorNombreCertificadoPaisEstado(String nombre, String certificado, int idPais, int estado,String fecInicio, String fecFin);
	
	//Para el crud
	public abstract Marca insertaActualizaMarca(Marca obj);
	public abstract List<Marca> listaMarcaPorNombreLike(String nombre);
	public abstract void eliminaMarca(int idMarca);
	public abstract Optional<Marca> buscarMarca(int idMarca);
		
	
	
}
