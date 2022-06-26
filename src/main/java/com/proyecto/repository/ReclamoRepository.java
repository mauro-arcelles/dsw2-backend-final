package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Reclamo;

public interface ReclamoRepository extends JpaRepository<Reclamo, Integer> {
	
	
	@Query("select r from Reclamo r where (?1 is '' or r.descripcion like ?1) and (r.estado = ?2) and (?3 is '' or ?4 is '' or (r.fechaCompra between ?3  and ?4 )) and (?5 is -1 or  r.tipoReclamo.idTipoReclamo = ?5) and (?6 is -1 or r.cliente.idCliente= ?6)")
	public List<Reclamo> listaReclamoParametros(String descripcion, int estado, String fecCom1, String fecCom2, int TipoReclamo, int Cliente);
	
	@Query("select x from Reclamo x where x.descripcion like ?1")
	public List<Reclamo> listaReclamoPorDescripcion(String descripcion);

}
