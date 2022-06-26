package com.proyecto.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Sede;
import com.proyecto.service.SedeService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/sede")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class SedeController {
	
	@Autowired
	SedeService service;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Sede>> listarSedes(){
		List<Sede> sedes = service.listarSedes();
		return ResponseEntity.ok(sedes);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertaSede(@RequestBody Sede obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
		Sede sedeNueva = service.registraSede(obj);
		
		if(sedeNueva == null) {
			salida.put("mensaje", "Error al registrar sede");
		}else {
			salida.put("mensaje", "Sede registrada correctamente");
		}
		
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/listaPorFiltros")
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> listaSedePorFiltros(
				@RequestParam(value = "nombre", required = false, defaultValue = "") String nombre,
				@RequestParam(value = "codigoPostal", required = false, defaultValue = "") String codigoPostal,
				@RequestParam(value = "idPais", required = false, defaultValue = "-1") int pais,
				@RequestParam(value = "estado", required = false, defaultValue = "-1") int estado
			){
		HashMap<String, Object> salida = new HashMap<>();
		try {
			List<Sede> lista = service.listaSedeNombreCodigoPostalPaisEstado("%"+nombre+"%", codigoPostal, pais, estado);
			if(CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			}else {
				salida.put("data", lista);
				salida.put("mensaje", "Existe " + lista.size() + " datos para mostrar");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			salida.put("mensaje", "Consulte con el administrador");
		}
		
		return ResponseEntity.ok(salida);
	}

}
