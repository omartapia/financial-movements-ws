package com.bcp.financial.movements.ws.repository;

import com.bcp.financial.movements.ws.model.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends CrudRepository<Cliente, Long> {

    @Query("select c from Cliente c where c.identificacion =:identificacion")
    Cliente findByIdentification(@Param("identificacion") String identificacion);
}
