package com.bcp.financial.movements.ws.services;

import com.bcp.financial.movements.ws.model.Movimiento;
import com.bcp.financial.movements.ws.repository.IMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {
    @Autowired
    private IMovimientoRepository repository;
    public List<Movimiento> findAll() {
        return (List<Movimiento>) repository.findAll();
    }

    public Optional<Movimiento> findById(long id) {
        return repository.findById(id);
    }

    public Movimiento save(Movimiento movimiento) {
        return repository.save(movimiento);
    }

    public Optional<Movimiento> update(Movimiento movimiento, Long id) {
        Optional<Movimiento> MovimientoDb = repository.findById(id);
        return MovimientoDb.map(entityToSave -> {
            entityToSave.setId(id);
            entityToSave.setFecha(movimiento.getFecha());
            entityToSave.setTipoMovimiento(movimiento.getTipoMovimiento());
            entityToSave.setValor(movimiento.getValor());
            entityToSave.setSaldo(movimiento.getSaldo());
            return repository.save(entityToSave);
        });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
