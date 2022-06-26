package com.proyecto.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.proyecto.service.ProveedorService;
import com.proyecto.util.AppSettings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Proveedor;

@RestController
@RequestMapping("/rest/proveedor")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)



public class ProveedorController {

	@Autowired
	private ProveedorService proveedorService;

	@GetMapping("/listarProveedor")
	@ResponseBody
	public ResponseEntity<List<Proveedor>> listaProveedor(){
		List<Proveedor> lista = proveedorService.listaProveedor();
		return ResponseEntity.ok(lista);
	}

	@PostMapping("/insertarProveedor")
	@ResponseBody
	public  ResponseEntity<Map<String, Object>> insertaProveedor(@RequestBody Proveedor obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			
			obj.setEstado(1); 
			obj.setFechaRegistro(new Date());
			
			Proveedor objSalida = proveedorService.insertaActualizaProveedor(obj);
			if (objSalida == null) {
				salida.put("mensaje", "NO REGISTRO, LLAME AL ADMINISTRADOR.");
			} else {
				salida.put("mensaje", "REGISTRADO");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "NO REGISTRO, LLAME AL ADMINISTRADOR.");
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
	
 @GetMapping("/listaProveedorConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaProveedorRazonRucDireccion(
			@RequestParam(name = "razonsocial", required = false, defaultValue = "") String razonsocial,
			@RequestParam(name = "ruc", required = false, defaultValue = "") String ruc,
			@RequestParam(name = "direccion", required = false, defaultValue = "") String direccion,
			@RequestParam(name = "estado", required = true, defaultValue = "1") int estado) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Proveedor> lista = proveedorService.listaProveedorRazonRucDireccion("%"+razonsocial+"%",ruc,direccion, estado);
			
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "NO HAY DATOS");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "HAY " + lista.size() + " ELEMENTOS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "ERROR");
		}
		return ResponseEntity.ok(salida);
	}
	

	@Autowired
	private ProveedorService service;
	
 @GetMapping("/listaProveedorPorRazonLike/{razon}")
	@ResponseBody
	public ResponseEntity<List<Proveedor>> listaProveedorPorRazonLike(@PathVariable("razon") String razon) {
		List<Proveedor> lista  = null;
		try {
			if (razon.equals("todos")) {
				lista = service.listaProveedorPorRazonLike("%");
			}else {
				lista = service.listaProveedorPorRazonLike("%" + razon + "%");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/registraProveedor")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaProveedorCrud(@RequestBody Proveedor obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdProveedor(0);
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Proveedor objSalida =  service.insertaActualizaProveedor(obj);
			if (objSalida == null) {
				salida.put("mensaje", "NO REGISTRO, LLAME AL ADMINISTRADOR.");
			} else {
				salida.put("mensaje"," REGISTRADO.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje",  "NO REGISTRO, LLAME AL ADMINISTRADOR.");
		}
		return ResponseEntity.ok(salida);
	}

	@PutMapping("/actualizaProveedor")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaProveedor(@RequestBody Proveedor obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Proveedor objSalida =  service.insertaActualizaProveedor(obj);
			if (objSalida == null) {
				salida.put("mensaje",  "NO ACTUALIZO, LLAME AL ADMINISTRADOR.");
			} else {
				salida.put("mensaje", "ACTUALIZADO.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje","NO ACTUALIZO, LLAME AL ADMINISTRADOR.");
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
	@DeleteMapping("/eliminaProveedor/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaDocente(@PathVariable("id")int id) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Optional<Proveedor> opt = service.buscaProveedor(id);
			if (opt.isPresent()) {
				service.eliminaProveedor(id);
				Optional<Proveedor> optDocente = service.buscaProveedor(id);
				if (optDocente.isEmpty()) {
					salida.put("mensaje","ELIMINADO");
				} else {
					salida.put("mensaje", "ERROR AL ELIMINAR");
				}
			}else {
				salida.put("mensaje","ID INEXISTENTE.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje","NO SE ELIMINO, REGISTRO RELACIONADO.");
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
	
	
}