package com.proyecto.entidad;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto.entidad.Ubigeo;

@Entity
@Table(name = "proveedor")
public class Proveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProveedor;
	private String razonsocial;
	private String ruc;
	private String direccion;
	private String telefono;
	private String celular;
	private String contacto;
	private int estado;
	
	@ManyToOne
	@JoinColumn(name = "idUbigeo")
	private Ubigeo ubigeo;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	
	private Date fechaRegistro;
	
       public Date getFechaRegistro() {
		return fechaRegistro;
	}
       
       public void setFechaRegistro(Date fechaRegistro) {
   		this.fechaRegistro = fechaRegistro;
   	}
	public int getEstado() {
		return estado;
	}
	public int getIdProveedor() {
		return idProveedor;
	}
	
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getRazonsocial() {
		return razonsocial;
	}

	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}




	public Ubigeo getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(Ubigeo ubigeo) {
		this.ubigeo = ubigeo;
	}
	
	
}