package com.proyecto.controller;

import java.util.*;

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

import com.proyecto.entidad.Reclamo;
import com.proyecto.service.*;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/reclamo")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ReclamoController {
	
	@Autowired
	private ReclamoService ReclamoService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Reclamo>> listaReclamo(){
		List<Reclamo> lista = ReclamoService.listaReclamo();
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaReclamo(@RequestBody Reclamo obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setFechaRegistro(new Date());
			obj.setIdReclamo(0);
			obj.setEstado(1);	
			Reclamo objSalida = ReclamoService.insertaActualizaReclamo(obj);
			if (objSalida == null) {
				salida.put("mensaje", "No se registró, consulte con el administrador.");
			}else {
				salida.put("mensaje", "Se registró correctamente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró, consulte con el administrador.");
		}
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/listaReclamoParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaReclamoParametros(
			@RequestParam(name = "descripcion", required = false, defaultValue = "") String descripcion,
			@RequestParam(name = "estado", required = true, defaultValue = "1") int estado,
			@RequestParam(name = "fecCom1", required = false, defaultValue = "") String fecCom1,
			@RequestParam(name = "fecCom2", required = false, defaultValue = "") String fecCom2,
			@RequestParam(name = "idTipoReclamo", required = false, defaultValue = "-1") int TipoReclamo,
			@RequestParam(name = "idCliente", required = false, defaultValue = "-1") int Cliente) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Reclamo> lista = ReclamoService.listaReclamoParametros("%"+descripcion+"%", estado, fecCom1, fecCom2,TipoReclamo, Cliente);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error!");
		}
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/listaReclamoPorDescripcion/{filtro}")
	@ResponseBody
	public ResponseEntity<List<Reclamo>> consulta(@PathVariable("filtro")String filtro)
	{	List<Reclamo> salida = null;
		try {
			if(filtro.equals("todos")) {
				salida = ReclamoService.listaReclamoPorDescripcion("%");	
			}else {
				salida = ReclamoService.listaReclamoPorDescripcion("%"+filtro+"%");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida);
	}

	@PostMapping("/crudRegistraReclamo")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> RegistraReclamo(@RequestBody Reclamo obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setFechaRegistro(new Date());
			obj.setIdReclamo(0);
			obj.setEstado(1);	
			Reclamo objSalida = ReclamoService.insertaActualizaReclamo(obj);
			if(objSalida==null) {
				salida.put("mensaje", "Error en el registro");
			} else {
				salida.put("mensaje", "Se ha registrado el reclamo exitosamente!");
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error en el registro");
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida); 
		}
	
	@PutMapping("/crudActualizaReclamo")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> ActualizaReclamo(@RequestBody Reclamo obj){
		Map<String, Object> salida = new HashMap<>();
		try {	
			Reclamo objSalida = ReclamoService.insertaActualizaReclamo(obj);
			if(objSalida==null) {
				salida.put("mensaje", "Error al actualizar registro");
			} else {
				salida.put("mensaje", "Se ha actualizado el reclamo exitosamente!");
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error en la actualización");
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida); 
		}
	
	@DeleteMapping("/crudEliminaReclamo/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> EliminaReclamo(@PathVariable("id") int id){
		Map<String, Object> salida = new HashMap<>();
		try {
			Optional<Reclamo> opt= ReclamoService.buscaReclamo(id);
			if(opt.isPresent())
			{	ReclamoService.EliminaReclamo(id);
				Optional<Reclamo> optreclamo = ReclamoService.buscaReclamo(id);
				if(optreclamo.isEmpty()) {
					salida.put("mensaje", "Registro eliminado exitosamente");
				} else {
					salida.put("mensaje", "Error al eliminar el reclamo!");
				}
			}else {
				salida.put("mensaje", "El id no existe!");
			}			
		} catch (Exception e) {
			salida.put("mensaje", "Error al eliminar el reclamo");
			e.printStackTrace();
		}
		return ResponseEntity.ok(salida); 
		}

}
