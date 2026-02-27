package org.demo.models;

public enum EspecialidadMecanico {

    FRENOSYTRANSMISION,
    SUSPENSION,
    BICICLETAELECTRICA;

    @Override
    public String toString() {
        return switch (this) {
            case FRENOSYTRANSMISION -> "Frenos y Transmisión";
            case SUSPENSION -> "Suspensión";
            case BICICLETAELECTRICA -> "Bicicletas Eléctricas";
        };
    }

}
