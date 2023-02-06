package com.bcp.financial.movements.ws.services;

import com.bcp.financial.movements.ws.exception.FinancialMovementsException;
import com.bcp.financial.movements.ws.model.Cuenta;
import com.bcp.financial.movements.ws.model.Movimiento;
import com.bcp.financial.movements.ws.model.vo.MovimientoVo;
import com.bcp.financial.movements.ws.repository.IMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    @Value("${limite.retiro}")
    private Float limiteRetiro;
    @Autowired
    private IMovimientoRepository repository;

    @Autowired
    private CuentaService service;
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
        Optional<Movimiento> movimientoDb = repository.findById(id);
        return movimientoDb.map(entityToSave -> {
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

    public MovimientoVo save(MovimientoVo movimientoVo) {
        return saveVo(movimientoVo);
    }

    private MovimientoVo saveVo(MovimientoVo movimientoVo) {
        Optional<Movimiento> movimientoDb = Optional.ofNullable(repository.save(entityMapper(movimientoVo)));
        return movimientoDb.map(movimiento -> voMapper(movimiento)).orElse(null);
    }

    private MovimientoVo voMapper(Movimiento movimientoDb) {
        return new MovimientoVo(movimientoDb.getId(),
                movimientoDb.getCuenta().getNumeroCuenta(),
                movimientoDb.getTipoMovimiento(),
                movimientoDb.getValor(),
                movimientoDb.getSaldo(),
                movimientoDb.getEstado(),
                buildMovimientoVo(movimientoDb));
    }

    private String buildMovimientoVo(Movimiento movimientoDb) {
        String movimiento;
        switch (movimientoDb.getTipoMovimiento()) {
            case CREDITO:
                movimiento = "Deposito de " + movimientoDb.getValor();
                break;
            case DEBITO:
                movimiento = "Retiro de " + movimientoDb.getValor();
                break;
            default:
                movimiento = null;
                break;
        }
        return movimiento;
    }

    private Movimiento entityMapper(MovimientoVo movimientoVo) {
        Cuenta cuenta = service.getByNumeroCuenta(movimientoVo.getNumeroCuenta());
        if (cuenta == null) {
            throw new FinancialMovementsException("La cuenta no existe.");
        }

        Movimiento movimiento = new Movimiento();
        movimiento.setCuenta(cuenta);
        movimientoVo.setSaldoInicial(cuenta.getSaldoInicial());
        List<Movimiento> movimientoAnterior = repository.getLastMovement(movimientoVo.getNumeroCuenta());
        if (movimientoAnterior.isEmpty()) {
            movimiento.setSaldo(buildSaldoMovimiento(cuenta.getSaldoInicial(), movimientoVo));
        } else {
            movimiento.setSaldo(buildSaldoMovimiento(movimientoAnterior.get(0).getSaldo(), movimientoVo));
        }
        movimiento.setFecha(new Date());
        movimiento.setTipoMovimiento(movimientoVo.getTipoMovimiento());
        movimiento.setValor(movimientoVo.getValor());
        movimiento.setEstado(movimientoVo.getEstado());
        return movimiento;
    }

    private Float buildSaldoMovimiento(Float saldo, MovimientoVo movimientoVo) {

        Float total;

        switch (movimientoVo.getTipoMovimiento()) {
            case CREDITO:
                total = saldo + movimientoVo.getValor();
                break;
            case DEBITO:
                total = saldo - movimientoVo.getValor();
                if(movimientoVo.getValor() > saldo) {
                    throw new FinancialMovementsException("Saldo no disponible");
                }
                if(saldo <= 0) {
                    throw new FinancialMovementsException("Saldo no disponible");
                }
                if (total > limiteRetiro) {
                    throw new FinancialMovementsException("Cupo diario Excedido");
                }
                break;
            default:
                total = 0F;
                break;
        }
        return total;
    }

}
