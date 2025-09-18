package com.example.demo.model;



public enum EstadoAlumno {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoAlumno(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    //  método para obtener el enum desde el string
    public static EstadoAlumno fromValor(String valor) {
        for (EstadoAlumno estado : values()) {
            if (estado.valor.equalsIgnoreCase(valor)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado inválido: " + valor);
    }
}