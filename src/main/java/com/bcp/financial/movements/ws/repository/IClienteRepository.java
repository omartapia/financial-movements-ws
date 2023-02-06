package com.bcp.financial.movements.ws.repository;

import com.bcp.financial.movements.ws.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends CrudRepository<Cliente, Long> {
}
