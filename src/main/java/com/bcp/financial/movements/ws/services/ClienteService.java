package com.bcp.financial.movements.ws.services;

import com.bcp.financial.movements.ws.model.Cliente;
import com.bcp.financial.movements.ws.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private IClienteRepository repository;
    public List<Cliente> findAll() {
        return (List<Cliente>) repository.findAll();
    }

    public Optional<Cliente> findById(long id) {
        return repository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public Optional<Cliente> update(Cliente cliente, Long id) {
        Optional<Cliente> ClienteDb = repository.findById(id);
        return ClienteDb.map(entityToSave -> {
            entityToSave.setClienteId(id);
            entityToSave.setContrasena(cliente.getContrasena());
            entityToSave.setEstado(cliente.getEstado());
            return repository.save(entityToSave);
        });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
