package com.bcp.financial.movements.ws.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteVo {

    private long id;

    @NotNull(message = "el campo es requerido")
    private String identificacion;

    @NotNull(message = "el campo es requerido")
    private String nombres;

    private String direccion;

    private String telefono;

    @NotNull(message = "el campo es requerido")
    private String contrasena;

    @NotNull(message = "el campo es requerido")
    private Boolean estado;

}
