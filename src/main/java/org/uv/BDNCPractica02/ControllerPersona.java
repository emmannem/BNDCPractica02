/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package org.uv.BDNCPractica02;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author JMRes
 */
@RestController
@RequestMapping("/api/persona")
public class ControllerPersona {

    @Autowired
    private RepositoryPersona repositoryPersona;

    @GetMapping()
    public List<Persona> list() {
        List<Persona> lstPersonas = repositoryPersona.findAll();
        if (!lstPersonas.isEmpty()) {
            return lstPersonas;
        } else {
            return null;
        }
    }

    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
        Optional<Persona> optPersona = repositoryPersona.findById(id);
        if (optPersona.isPresent()) {
            return ResponseEntity.ok().body(optPersona);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> put(@PathVariable String id, @RequestBody Persona persona) {
        Optional<Persona> dataPersona = repositoryPersona.findById(id);
        if (dataPersona.isPresent()) {
            Persona _persona = dataPersona.get();
            _persona.setNombre(persona.getNombre());
            _persona.setDireccion(persona.getDireccion());
            _persona.setTelefono(persona.getTelefono());
            repositoryPersona.save(_persona);
            return ResponseEntity.ok().body(_persona);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Persona> post(@RequestBody Persona persona) {
        try {
            Persona savedPersona = repositoryPersona.save(persona);
            return ResponseEntity.ok().body(savedPersona);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Persona> delete(@PathVariable String id) {
        Optional<Persona> optEmpleado = repositoryPersona.findById(id);
        if (optEmpleado.isPresent()) {
            try {
                repositoryPersona.deleteById(id);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
