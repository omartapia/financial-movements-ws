package com.bcp.financial.movements.ws.repository;

import com.bcp.financial.movements.ws.model.Movimiento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface IMovimientoRepository extends CrudRepository<Movimiento, Long> {

    @Query("select m from Movimiento m where m.cuenta.numeroCuenta =:numeroCuenta order by m.fecha desc")
    List<Movimiento> getLastMovement(@Param("numeroCuenta") String numeroCuenta);

}
