package com.bcp.financial.movements.ws.controllers;

import com.bcp.financial.movements.ws.config.Config;
import com.bcp.financial.movements.ws.model.Cuenta;
import com.bcp.financial.movements.ws.model.Persona;
import com.bcp.financial.movements.ws.services.CuentaService;
import com.bcp.financial.movements.ws.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping(Config.CUENTAS)
public class CuentaController {
    @Autowired
    private CuentaService service;

    @GetMapping
    public ResponseEntity<List<Cuenta>> findAll() {
        try {
            List<Cuenta> cuentas = service.findAll();
            if (cuentas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cuentas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(Config.ID)
    public ResponseEntity<Cuenta> getById(@PathVariable("id") long id) {
        Optional<Cuenta> _cuenta = service.findById(id);
        return _cuenta.map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Cuenta> save(@RequestBody Cuenta cuenta) {
        try {
            Cuenta _cuenta = service.save(cuenta);
            return new ResponseEntity<>(_cuenta, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(Config.ID)
    public ResponseEntity<Cuenta> update(@RequestBody Cuenta cuenta, @PathVariable("id") Long id) {
        Optional<Cuenta> _cuenta = service.update(cuenta, id);
        return _cuenta.map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(Config.ID)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
         service.delete(id);
    }
}