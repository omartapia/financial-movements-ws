package com.bcp.financial.movements.ws.repository;

import com.bcp.financial.movements.ws.model.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends CrudRepository<Persona, Long> {
}
