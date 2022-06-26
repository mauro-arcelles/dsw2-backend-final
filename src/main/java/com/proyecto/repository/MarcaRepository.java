package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Integer>{
	
	@Query("select x from Marca x where (?1 is '' or x.nombre like ?1) and (?2 is '' or x.certificado like ?2) and (?3 is -1 or x.pais.idPais = ?3) and x.estado = ?4 and (?5 is '' or ?6 is '' or (x.fechaRegistro between ?5  and ?6 ))")       
	public List<Marca> listaMarcaPorNombreCertificadoPaisEstado(String nombre, String certificado, int idPais, int estado,String fecInicio, String fecFin);
	
	
	@Query("select x from Marca x where x.nombre like ?1")
	public List<Marca> listaPorNombreMarcaLike(String nombre);

}
