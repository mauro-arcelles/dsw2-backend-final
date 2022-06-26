package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Sede;

public interface SedeRepository extends JpaRepository<Sede, Integer>{

	@Query("select s from Sede s where (?1 is '' or s.nombre like ?1) and (?2 is '' or s.codigoPostal = ?2) and (?3 is -1 or s.pais.idPais = ?3) and "
			+ "(?4 is -1 or s.estado = ?4)")
	public List<Sede> listaSedeNombreCodigoPostalPaisEstado(String nombre, String codigoPostal, int pais, int estado);
	
	@Query("select s from Sede s where s.nombre like ?1")
	public List<Sede> listaSedePorNombreLike(String nombre);
	
}
