package com.bcp.financial.movements.ws.enums;

import lombok.Getter;

public enum TipoMovimiento {
    DEPOSITO("Deposito"),RETIRO("Retiro");

    @Getter
    private String value;
    TipoMovimiento(String value) {
        this.value = value;
    }
}
