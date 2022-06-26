package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.proyecto.entidad.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

	@Query("select x from Proveedor x where (?1 is '' or x.razonsocial like ?1) and (?2 is '' or x.ruc = ?2) and (?3 is '' or x.direccion = ?3) and x.estado = ?4")       
	public List<Proveedor> listaProveedorRazonRucDireccion(String razonsocial, String ruc, String direccion, int estado);
	
	@Query("select x from Proveedor x where x.razonsocial like ?1")
	public List<Proveedor> listaPorRazonLike(String nombre);
}