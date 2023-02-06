package com.bcp.financial.movements.ws.services;

import com.bcp.financial.movements.ws.repository.IPersonaRepository;
import com.bcp.financial.movements.ws.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private IPersonaRepository repository;
    public List<Persona> findAll() {
        return (List<Persona>) repository.findAll();
    }

    public Optional<Persona> findById(long id) {
        return repository.findById(id);
    }

    public Persona save(Persona persona) {
        return repository.save(persona);
    }

    public Optional<Persona> update(Persona persona, Long id) {
        Optional<Persona> personaDb = repository.findById(id);
        return personaDb.map(entityToSave -> {
            entityToSave.setId(id);
            entityToSave.setIdentificacion(persona.getIdentificacion());
            entityToSave.setNombre(persona.getNombre());
            entityToSave.setEdad(persona.getEdad());
            entityToSave.setGenero(persona.getGenero());
            entityToSave.setDireccion(persona.getDireccion());
            entityToSave.setTelefono(persona.getTelefono());
            return repository.save(entityToSave);
        });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
