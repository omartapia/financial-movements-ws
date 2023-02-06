package com.bcp.financial.movements.ws.services;

import com.bcp.financial.movements.ws.model.Cuenta;
import com.bcp.financial.movements.ws.repository.ICuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {
    @Autowired
    private ICuentaRepository repository;
    public List<Cuenta> findAll() {
        return (List<Cuenta>) repository.findAll();
    }

    public Optional<Cuenta> findById(long id) {
        return repository.findById(id);
    }

    public Cuenta save(Cuenta cuenta) {
        return repository.save(cuenta);
    }

    public Optional<Cuenta> update(Cuenta cuenta, Long id) {
        Optional<Cuenta> CuentaDb = repository.findById(id);
        return CuentaDb.map(entityToSave -> {
            entityToSave.setId(id);
            entityToSave.setNumeroCuenta(cuenta.getNumeroCuenta());
            entityToSave.setTipoCuenta(cuenta.getTipoCuenta());
            entityToSave.setSaldoInicial(cuenta.getSaldoInicial());
            entityToSave.setEstado(cuenta.getEstado());
            return repository.save(entityToSave);
        });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
