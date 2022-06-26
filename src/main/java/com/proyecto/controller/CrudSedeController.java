package com.proyecto.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Sede;
import com.proyecto.service.SedeService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/crudSede")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class CrudSedeController {

	@Autowired
	SedeService service;
	
	@GetMapping("/listaSedePorNombreLike/{nombre}")
	@ResponseBody
	public ResponseEntity<List<Sede>> lista(@PathVariable("nombre") String nombre){
		List<Sede> salida = new ArrayList<>();
		try {
			salida = service.listaSedePorNombreLike("%"+nombre+"%");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);
	}
	
	
	@PostMapping("/registraSede")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertaSede(@RequestBody Sede obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			obj.setIdSede(0);
			Sede objSalida = service.insertaActualizaSede(obj);
			if(objSalida == null) {
				salida.put("mensaje", "Hubo un error al registrar la sede.");
			}else {
				salida.put("mensaje", "Se registró correctamente la sede.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			salida.put("mensaje", "Hubo un error al registrar la sede: " + e.getMessage());
		}
		
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/actualizaSede")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> actualizaSede(@RequestBody Sede obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		int id = obj.getIdSede();
		if(id > 0) {
			try {
				Sede objSalida = service.insertaActualizaSede(obj);
				if(objSalida == null) {
					salida.put("mensaje", "Hubo un error al actualizar la sede.");
				}else {
					salida.put("mensaje", "Se actulizó correctamente la sede.");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				salida.put("mensaje", "Hubo un error al actualizar la sede: " + e.getMessage());
			}
			
		}else {
			salida.put("mensaje", "No se encontró dato para actualizar con ese ID");
		}
		
		return ResponseEntity.ok(salida);
		
	}
	
	@DeleteMapping("/eliminaSede/{id}")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> eliminaSede(@PathVariable("id") int id){
		HashMap<String , Object> salida = new HashMap<>();
		try {
			Optional<Sede> opt = service.buscaSede(id);
			if(!opt.isEmpty()) {
				service.eliminaSede(id);
				salida.put("mensaje", "Se eliminó sede correctamente");
			}else {
				salida.put("salida", "Error al intentar eliminar la sede con id: " + id+ ". Puede que no exista registro con ese id");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			salida.put("mensaje", "Error al eliminar sede: " + e.getMessage());
		}
		return ResponseEntity.ok(salida);
	}
	
}
