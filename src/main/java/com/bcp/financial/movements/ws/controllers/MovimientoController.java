package com.bcp.financial.movements.ws.controllers;

import com.bcp.financial.movements.ws.config.Config;
import com.bcp.financial.movements.ws.model.Movimiento;
import com.bcp.financial.movements.ws.services.MovimientoService;
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
@RequestMapping(Config.MOVIMIENTOS)
public class MovimientoController {
    @Autowired
    private MovimientoService service;

    @GetMapping
    public ResponseEntity<List<Movimiento>> findAll() {
        try {
            List<Movimiento> movimientos = service.findAll();
            if (movimientos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(movimientos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(Config.ID)
    public ResponseEntity<Movimiento> getById(@PathVariable("id") long id) {
        Optional<Movimiento> _movimiento = service.findById(id);
        return _movimiento.map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Movimiento> save(@RequestBody Movimiento movimiento) {
        try {
            Movimiento _movimiento = service.save(movimiento);
            return new ResponseEntity<>(_movimiento, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(Config.ID)
    public ResponseEntity<Movimiento> update(@RequestBody Movimiento movimiento, @PathVariable("id") Long id) {
        Optional<Movimiento> _movimiento = service.update(movimiento, id);
        return _movimiento.map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(Config.ID)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletee(@PathVariable("id") Long id) {
         service.delete(id);
    }
}