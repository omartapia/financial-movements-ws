package com.bcp.financial.movements.ws.repository;

import com.bcp.financial.movements.ws.model.Movimiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovimientoRepository extends CrudRepository<Movimiento, Long> {
}
