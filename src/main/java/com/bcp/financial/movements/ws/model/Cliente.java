package com.bcp.financial.movements.ws.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
@Data
@EqualsAndHashCode(callSuper = false)
public class Cliente extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 300)
    private String contrasena;

    private Boolean estado;

    public Cliente() {

    }

    public Cliente(String identificacion) {
        this.identificacion = identificacion;
    }
}
