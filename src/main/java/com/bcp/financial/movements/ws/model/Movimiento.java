package com.bcp.financial.movements.ws.model;

import com.bcp.financial.movements.ws.enums.TipoMovimiento;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "movimiento")
@Data
public class Movimiento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date fecha;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TipoMovimiento tipoMovimiento;
    private Float valor;
    private Float saldo;

    private Boolean estado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;
}
