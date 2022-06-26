package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.Reclamo;

public interface ReclamoService {
	
	public Reclamo insertaActualizaReclamo(Reclamo obj);
	public List<Reclamo> listaReclamo();
	public abstract List<Reclamo> listaReclamoParametros(String descripcion, int estado, String fecCom1, String fecCom2, int TipoReclamo, int Cliente);
	public abstract Reclamo InsertaActualizaReclamo(Reclamo obj);
	public abstract List<Reclamo> listaReclamoPorDescripcion(String descripcion);
	public abstract void EliminaReclamo(int id);
	public abstract Optional<Reclamo> buscaReclamo(int id);
}
