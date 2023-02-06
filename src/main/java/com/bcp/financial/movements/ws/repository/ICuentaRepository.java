package com.bcp.financial.movements.ws.repository;

import com.bcp.financial.movements.ws.model.Cuenta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICuentaRepository extends CrudRepository<Cuenta, Long> {
}
