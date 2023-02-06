package com.bcp.financial.movements.ws.services;

import com.bcp.financial.movements.ws.model.Cliente;
import com.bcp.financial.movements.ws.model.vo.ClienteVo;
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
        Optional<Cliente> clienteDb = repository.findById(id);
        return clienteDb.map(entity -> {
            Cliente response = repository.save(entityMapper(entity, cliente));
            return response;
        });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public ClienteVo save(ClienteVo clienteVo) {
        return saveVo(clienteVo);
    }

    private ClienteVo saveVo(ClienteVo clienteVo) {
        Optional<Cliente> clienteDb = Optional.ofNullable(repository.save(entityMapper(clienteVo)));
        return clienteDb.map(cliente -> voMapper(cliente)).orElse(null);
    }

    private ClienteVo voMapper(Cliente clienteDb) {
        return new ClienteVo(clienteDb.getId(),
                clienteDb.getIdentificacion(),
                clienteDb.getNombres(),
                clienteDb.getDireccion(),
                clienteDb.getTelefono(),
                clienteDb.getContrasena(),
                clienteDb.getEstado());
    }

    private Cliente entityMapper(ClienteVo clienteVo) {
        Cliente cliente = new Cliente();
        cliente.setContrasena(clienteVo.getContrasena());
        cliente.setEstado(clienteVo.getEstado());
        cliente.setNombres(clienteVo.getNombres());
        cliente.setDireccion(clienteVo.getDireccion());
        cliente.setTelefono(clienteVo.getTelefono());
        cliente.setIdentificacion(clienteVo.getIdentificacion());
        return cliente;
    }

    private Cliente entityMapper(Cliente entity, Cliente cliente) {
        entity.setContrasena(cliente.getContrasena());
        entity.setEstado(cliente.getEstado());
        return entity;
    }

    public Cliente getByIdentification(String identificacion) {
        return Optional.ofNullable(repository
                        .findByIdentification(identificacion))
                .orElse(new Cliente(identificacion));
    }
}
