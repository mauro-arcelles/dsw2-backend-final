package com.proyecto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.proyecto.entidad.Marca;
import com.proyecto.service.MarcaService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/marca")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class MarcaController {
	
	@Autowired
	private MarcaService marcaService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Marca>> listaMarca(){
		List<Marca> lista = marcaService.listaMarca();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaMarca(@RequestBody Marca obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			Marca objSalida = marcaService.insertaActualizaMarca(obj);
			if (objSalida == null) {
				salida.put("mensaje", "No se registro, consulte con el administrador.");
			}else {
				salida.put("mensaje", "Se registro correctamente.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje","No se registro, consulte con el administrador.");
		}
		return ResponseEntity.ok(salida);
	}
	
	@GetMapping("/listaMarcaPorParametros")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listaMarcaPorNombreCertificadoPaisEstado(
            @RequestParam(name = "nombre", required = false, defaultValue = "") String nombre,
            @RequestParam(name = "certificado", required = false, defaultValue = "") String certificado,
            @RequestParam(name = "idPais", required = false, defaultValue = "-1") int idPais,
			@RequestParam(name="estado", required =false, defaultValue ="1") int estado,
			@RequestParam(name = "fechaInicio", required = false, defaultValue = "") String fechaInicio,
			@RequestParam(name = "fechaFin", required = false, defaultValue = "") String fechaFin)
    {
        Map<String, Object> salida = new HashMap<>();

        try {
            List<Marca> lista = marcaService.listaMarcaPorNombreCertificadoPaisEstado("%" + nombre + "%", "%" + certificado + "%", idPais, estado,fechaInicio, fechaFin);
            if (CollectionUtils.isEmpty(lista)) {
                salida.put("mensaje", "No existe datos para mostrar");
            } else {
                salida.put("data", lista);
                salida.put("mensaje", "existe " + lista.size() + " datos para mostrar");
            }
        } catch (Exception e) {
            e.printStackTrace();
            salida.put("mensaje", "No se registró, consulte con el administrador.");
        }

        return ResponseEntity.ok(salida);

    }

}
