package com.bcp.financial.movements.ws.model.vo;

import com.bcp.financial.movements.ws.enums.TipoCuenta;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CuentaVo {
    private long id;

    @NotNull(message = "el campo es requerido")
    private String numeroCuenta;
    @NotNull(message = "el campo es requerido")
    private TipoCuenta tipoCuenta;
    @NotNull(message = "el campo es requerido")
    private Float saldoInicial;

    @NotNull(message = "el campo es requerido")
    private Boolean estado;

    @NotNull(message = "el campo es requerido")
    private String cliente;
    @NotNull(message = "el campo es requerido")
    private String identificacion;

}
