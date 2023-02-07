package com.bcp.financial.movements.ws.services;

import com.bcp.financial.movements.ws.enums.Genero;
import com.bcp.financial.movements.ws.model.Cliente;
import com.bcp.financial.movements.ws.repository.IClienteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClienteService service;
    @MockBean
    private IClienteRepository repository;

    @Test
    public void givenARequestThenReturnClientsList() {
        // given
        Cliente cliente = mockCliente();

        // when
        when(repository.findAll())
                .thenReturn(Collections.singletonList(cliente));

        List<Cliente> result = service.findAll();

        //then
        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getId(), cliente.getId());
        assertEquals(result.get(0).getIdentificacion(), cliente.getIdentificacion());
        assertEquals(result.get(0).getNombres(), cliente.getNombres());
        assertEquals(result.get(0).getEstado(), cliente.getEstado());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void givenAClientIdThenReturnClientsEntity() {
        // given
        Cliente cliente = mockCliente();

        // when
        when(repository.findById(anyLong())).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = service.findById(anyLong());

        //then
        assertTrue(result.isPresent());
        assertEquals(result.get().getId(), cliente.getId());
        assertEquals(result.get().getIdentificacion(), cliente.getIdentificacion());
        assertEquals(result.get().getNombres(), cliente.getNombres());
        assertEquals(result.get().getEstado(), cliente.getEstado());
        verify(repository, times(1)).findById(anyLong());
    }

    private Cliente mockCliente() {
        Cliente cliente = new Cliente("1000000008");
        cliente.setId(1L);
        cliente.setEstado(true);
        cliente.setNombres("muy muy");
        cliente.setDireccion("adress muy muy");
        cliente.setContrasena("1234");
        cliente.setEdad(20);
        cliente.setGenero(Genero.M);
        cliente.setTelefono("22222");
        return cliente;
    }
}
