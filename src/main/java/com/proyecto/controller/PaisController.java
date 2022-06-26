package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Pais;
import com.proyecto.service.PaisService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/util")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class PaisController {
	
	@Autowired
	PaisService service;
	
	@GetMapping("/{listaPais}")
	@ResponseBody
	public ResponseEntity<List<Pais>> listarPaises(){
		List<Pais> paises = service.listaPais();
		return ResponseEntity.ok(paises);
	}

}
