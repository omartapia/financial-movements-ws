package com.bcp.financial.movements.ws.model;

import com.bcp.financial.movements.ws.enums.Genero;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "persona")
@Data
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;

    @Enumerated(EnumType.STRING)
    private Genero genero;
    private String edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
