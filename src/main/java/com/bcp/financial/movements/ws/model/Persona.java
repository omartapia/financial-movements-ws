package com.bcp.financial.movements.ws.model;

import com.bcp.financial.movements.ws.enums.Genero;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;


@MappedSuperclass
@Data
public class Persona implements Serializable {
    @Column(length = 100)
    protected String nombres;

    @Enumerated(EnumType.STRING)
    @Column(length = 5)
    protected Genero genero;
    protected Integer edad;
    @Column(length = 20)
    protected String identificacion;
    @Column(length = 500)
    protected String direccion;
    @Column(length = 20)
    protected String telefono;
}
