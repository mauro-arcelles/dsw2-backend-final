package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.Sede;

public interface SedeService {
	
	public abstract List<Sede> listarSedes();
	
	public abstract Sede registraSede(Sede obj);
	
	public abstract List<Sede> listaSedeNombreCodigoPostalPaisEstado(String nombre, String codigoPostal, int pais, int estado);
	
	public abstract Sede insertaActualizaSede(Sede obj);
	
	public abstract List<Sede> listaSedePorNombreLike(String nombre);
	
	public abstract void eliminaSede(int id);
	
	public abstract Optional<Sede> buscaSede(int id);

}

