package com.proyecto.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Sede;
import com.proyecto.repository.SedeRepository;

@Service
public class SedeServiceImpl implements SedeService{
	
	@Autowired
	SedeRepository repository;
	
	String pattern1 = "yyyy-MM-dd";
	String pattern2 = "yyyy-MM-dd hh:mm:ss";
	SimpleDateFormat format1 = new SimpleDateFormat(pattern1);
	SimpleDateFormat format2 = new SimpleDateFormat(pattern2);

	@Override
	public Sede registraSede(Sede obj) {
		// TODO Auto-generated method stub
		String date = format1.format(new Date());
		String longDate = format2.format(new Date());
		try {
			Date hoy = format1.parse(date);
			Date hoyLargo = format2.parse(longDate);
			obj.setFechaCreacion(hoy);
			obj.setFechaRegistro(hoyLargo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return repository.save(obj);
	}

	@Override
	public List<Sede> listarSedes() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public List<Sede> listaSedeNombreCodigoPostalPaisEstado(String nombre, String codigoPostal, int pais, int estado) {
		// TODO Auto-generated method stub
		return repository.listaSedeNombreCodigoPostalPaisEstado(nombre, codigoPostal, pais, estado);
	}

	@Override
	public Sede insertaActualizaSede(Sede obj) {
		// TODO Auto-generated method stub
		return repository.save(obj);
	}
	
	@Override
	public List<Sede> listaSedePorNombreLike(String nombre) {
		// TODO Auto-generated method stub
		return repository.listaSedePorNombreLike(nombre);
	}
	
	@Override
	public void eliminaSede(int id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public Optional<Sede> buscaSede(int id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}


}
