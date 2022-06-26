package com.proyecto.controller;

import java.util.*;

import ch.qos.logback.core.net.server.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.proyecto.entidad.Cliente;
import com.proyecto.service.ClienteService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/cliente")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Map<String, Object>> insertaModalidad(@RequestBody Cliente cliente) {
        Map<String, Object> salida = new HashMap<>();
        try {
            cliente.setFechaRegistro(new Date());
            cliente.setEstado(1);
            Cliente clienteInsertado = clienteService.insertaActualizaCliente(cliente);
            if (clienteInsertado == null) {
                salida.put("mensaje", "Error al registrar el cliente.");
            } else {
                salida.put("mensaje", "Cliente registrado correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            salida.put("mensaje", "Error al registrar el cliente, consulte con el administrador.");
        }
        return ResponseEntity.ok(salida);
    }

    @GetMapping("/listaPorParametros")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listaClienteNombreDniUbigeoEstado(
            @RequestParam(name = "nombre", required = false, defaultValue = "") String nombre,
            @RequestParam(name = "dni", required = false, defaultValue = "") String dni,
            @RequestParam(name = "idUbigeo", required = false, defaultValue = "-1") int idUbigeo,
			@RequestParam(name="estado", required =false, defaultValue ="1") int estado)
    {
        Map<String, Object> salida = new HashMap<>();

        try {
            List<Cliente> lista = clienteService.listaClienteNombreDniUbigeoEstado("%" + nombre + "%", dni, idUbigeo, estado);
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

    @GetMapping("/listaClientePorNombreLike/{nom}")
    @ResponseBody
    public ResponseEntity<List<Cliente>> listaClientePorNombreLike(@PathVariable("nom") String nom) {
        List<Cliente> lista = null;
        try {
            if (nom.equals("todos")) {
                lista = clienteService.listaClientePorNombreLike("%");
            } else {
                lista = clienteService.listaClientePorNombreLike("%" + nom + "%");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/registraCliente")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> insertaCliente(@RequestBody Cliente obj) {
        Map<String, Object> salida = new HashMap<>();
        try {
            Cliente cliente = clienteService.porCorreo(obj.getCorreo());
            if (cliente != null) {
                salida.put("mensaje", "El correo ya existe.");
            } else {
                obj.setIdCliente(0);
                obj.setFechaRegistro(new Date());
                obj.setEstado(1);
                Cliente objSalida = clienteService.insertaActualizaCliente(obj);
                if (objSalida == null) {
                    salida.put("mensaje", "No se registró, consulte con el administrador.");
                } else {
                    salida.put("mensaje", "Se registró correctamente.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            salida.put("mensaje", "No se registró, consulte con el administrador.");
        }
        return ResponseEntity.ok(salida);
    }

    @PutMapping("/actualizaCliente")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> actualizaCliente(@RequestBody Cliente obj) {
        Map<String, Object> salida = new HashMap<>();
        try {
            Cliente cliente = clienteService.porCorreo(obj.getCorreo());
            if (cliente != null && cliente.getIdCliente() != obj.getIdCliente()) {
                salida.put("mensaje", "El correo ya existe.");

            } else {
                Cliente objSalida = clienteService.insertaActualizaCliente(obj);
                if (objSalida == null) {
                    salida.put("mensaje", "No se actualizó, consulte con el administrador.");
                } else {
                    salida.put("mensaje", "Se actualizó correctamente.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            salida.put("mensaje", "No se actualizó, consulte con el administrador.");
        }
        return ResponseEntity.ok(salida);
    }

    @DeleteMapping("/eliminaCliente/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> eliminaCliente(@PathVariable("id") int id) {

        Map<String, Object> salida = new HashMap<>();

        try {
            Optional<Cliente> opt = clienteService.buscaCliente(id);
            if (opt.isPresent()) {
                clienteService.eliminaCliente(id);

                Optional<Cliente> optCliente = clienteService.buscaCliente(id);
                if (optCliente.isEmpty()) {
                    salida.put("mensaje", "Se eliminó correctamente.");
                } else {
                    salida.put("mensaje", "No se eliminó, ya que el registro esta relacionado.");
                }
            } else {
                salida.put("mensaje", "No existe el ID que se desea eliminar.");
            }


        } catch (Exception e) {
            e.printStackTrace();
            salida.put("mensaje", "No se eliminó, ya que el registro esta relacionado.");

        }

        return ResponseEntity.ok(salida);

    }

}
