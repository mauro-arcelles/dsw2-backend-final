package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.entidad.Cliente;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("select c from Cliente c where (?1 is '' or c.nombres like ?1) and (?2 is '' or c.dni = ?2) and (?3 is -1 or c.ubigeo.idUbigeo = ?3) and c.estado = ?4 ")
    public List<Cliente> listaClienteNombreDniUbigeoEstado(String nombre, String dni, int idUbigeo,int estado);

    @Query("select x from Cliente x where x.nombres like ?1")
    public List<Cliente> listaPorNombreLike(String nombre);

    public Cliente findByCorreo(String correo);

}
