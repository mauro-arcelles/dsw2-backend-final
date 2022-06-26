package com.proyecto.service;

import java.util.List;
import java.util.Optional;


import com.proyecto.entidad.Proveedor;

public interface ProveedorService {

	
	public abstract Proveedor insertaActualizaProveedor(Proveedor obj) ;
	public abstract List<Proveedor> listaProveedor() ;
	public abstract List<Proveedor> listaProveedorRazonRucDireccion(String razonsocial, String ruc, String direccion, int estado);
	public abstract void eliminaProveedor(int id);
	public abstract List<Proveedor> listaProveedorPorRazonLike(String razonsocial);
	public abstract Optional<Proveedor> buscaProveedor(int id);
	}