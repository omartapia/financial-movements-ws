package com.bcp.financial.movements.ws.repository;

import com.bcp.financial.movements.ws.model.Cuenta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICuentaRepository extends CrudRepository<Cuenta, Long> {

    @Query("select c from Cuenta c where c.numeroCuenta =:numeroCuenta")
    Cuenta getByNumeroCuenta(@Param("numeroCuenta") String numeroCuenta);
}
