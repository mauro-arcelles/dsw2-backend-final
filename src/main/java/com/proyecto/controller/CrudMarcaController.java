package com.proyecto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.proyecto.entidad.Marca;
import com.proyecto.service.MarcaService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/marca")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class CrudMarcaController{
	
	@Autowired
	private MarcaService marcaService;
	
	@GetMapping("/crudMarca/listaMarcaPorNombreLike/{nom}")
	@ResponseBody
	public ResponseEntity<List<Marca>> listaMarcaPorNombreLike(@PathVariable("nom") String nom) {
		List<Marca> lista = null;
		try {
			if (nom.equals("todos")) {
				lista = marcaService.listaMarcaPorNombreLike("%");
			}else {
				lista = marcaService.listaMarcaPorNombreLike("%" + nom + "%");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/crudMarca/registrarMarca")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaMarca(@RequestBody Marca obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdMarca(0);
			Marca objSalida = marcaService.insertaActualizaMarca(obj);
			if (objSalida == null) {
				salida.put("mensaje","No se registró, consulte con el administrador." );
			} else {
				salida.put("mensaje","Se registró correctamente." );
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró, consulte con el administrador.");
		}
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/crudMarca/actualizaMarca")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaDocente(@RequestBody Marca obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Marca objSalida =  marcaService.insertaActualizaMarca(obj);
			if (objSalida == null) {
				salida.put("mensaje", "No se actualizó, consulte con el administrador.");
			} else {
				salida.put("mensaje", "Se actualizó correctamente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se actualizó, consulte con el administrador.");
		}
		return ResponseEntity.ok(salida);
	}
	
	@DeleteMapping("/crudMarca/eliminaMarca/{idMarca}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaMarca(@PathVariable("idMarca") int idMarca) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Optional<Marca> opt = marcaService.buscarMarca(idMarca);
			if (opt.isPresent()) {
				marcaService.eliminaMarca(idMarca);
				Optional<Marca> optMarca = marcaService.buscarMarca(idMarca);
				if (optMarca.isEmpty()) {
					salida.put("mensaje", "Se elimino correctamente.");
				} else {
					salida.put("mensaje", "No se elimino");
				}
			} else {
				salida.put("mensaje", "No existe el codigo");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se actualizó, consulte con el administrador.");
		}
		return ResponseEntity.ok(salida);
	}
}
