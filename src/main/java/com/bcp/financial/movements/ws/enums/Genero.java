package com.bcp.financial.movements.ws.enums;

import lombok.Getter;

public enum Genero {
    MASCULINO("M"), FEMENINO("F");

    @Getter
    private String value;
    Genero(String value) {
        this.value = value;
    }
}
