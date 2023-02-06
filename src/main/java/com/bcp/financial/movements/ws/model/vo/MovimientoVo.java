package com.bcp.financial.movements.ws.model.vo;

import com.bcp.financial.movements.ws.enums.TipoCuenta;
import com.bcp.financial.movements.ws.enums.TipoMovimiento;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MovimientoVo {

    private long id;

    @NotNull(message = "el campo es requerido")
    private String numeroCuenta;

    @NotNull(message = "el campo es requerido")
    private TipoMovimiento tipoMovimiento;
    private Float saldoInicial;

    @NotNull(message = "el campo es requerido")
    private Float valor;
    @NotNull(message = "el campo es requerido")
    private Boolean estado;

    private String movimiento;

}
