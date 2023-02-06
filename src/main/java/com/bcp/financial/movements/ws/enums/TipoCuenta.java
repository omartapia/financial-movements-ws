package com.bcp.financial.movements.ws.enums;

import lombok.Getter;

public enum TipoCuenta {
    AHORROS("Ahorros"),CORRIENTE("Corriente");

    @Getter
    private String value;
    TipoCuenta(String value) {
        this.value = value;
    }
}
