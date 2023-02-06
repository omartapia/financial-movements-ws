package com.bcp.financial.movements.ws.services;

import com.bcp.financial.movements.ws.exception.FinancialMovementsException;
import com.bcp.financial.movements.ws.model.Cliente;
import com.bcp.financial.movements.ws.model.Cuenta;
import com.bcp.financial.movements.ws.model.vo.CuentaVo;
import com.bcp.financial.movements.ws.repository.ICuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {
    @Autowired
    private ICuentaRepository repository;

    @Autowired
    private ClienteService service;

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
        Optional<Cuenta> cuentaDb = repository.findById(id);
        return cuentaDb.map(entityToSave -> {
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

    public CuentaVo save(CuentaVo cuentaVo) {
        return saveVo(cuentaVo);
    }

    private CuentaVo saveVo(CuentaVo cuentaVo) {
        Optional<Cuenta> cuentaDb = Optional.ofNullable(repository.save(entityMapper(cuentaVo)));
        return cuentaDb.map(cuenta -> voMapper(cuenta)).orElse(null);
    }

    private CuentaVo voMapper(Cuenta cuentaDb) {
        return new CuentaVo(cuentaDb.getId(),
                cuentaDb.getNumeroCuenta(),
                cuentaDb.getTipoCuenta(),
                cuentaDb.getSaldoInicial(),
                cuentaDb.getEstado(),
                cuentaDb.getCliente().getNombres(),
                cuentaDb.getCliente().getIdentificacion());
    }

    private Cuenta entityMapper(CuentaVo cuentaVo) {
        Cuenta cuenta = new Cuenta();
        Cliente cliente = service.getByIdentification(cuentaVo.getIdentificacion());
        if (cliente == null) {
            throw  new FinancialMovementsException("El cliente no existe para esta cuenta.");
        }
        if(cuentaVo.getSaldoInicial() <= 0) {
            throw new FinancialMovementsException("Saldo no puede ser menor a cero");
        }
        cuenta.setNumeroCuenta(cuentaVo.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaVo.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaVo.getSaldoInicial());
        cuenta.setEstado(cuentaVo.getEstado());
        cuenta.setCliente(cliente);
        cuenta.getCliente().setEstado(cuentaVo.getEstado());
        cuenta.getCliente().setNombres(cuentaVo.getCliente());
        return cuenta;
    }

    public Cuenta getByNumeroCuenta(String numeroCuenta) {
        return Optional.ofNullable(repository
                        .getByNumeroCuenta(numeroCuenta))
                .orElse(null);
    }
}
