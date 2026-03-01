package org.demo.models;

public enum MotivoReparacion {

    MANTENIMIENTO_GENERAL("Mantenimiento general"),
    PINCHAZO("Pinchazo"),
    CAMBIO_CADENA("Cambio de cadena"),
    AJUSTE_FRENOS("Ajuste de frenos"),
    AJUSTE_CAMBIOS("Ajuste de cambios"),
    CENTRADO_RUEDA("Centrado de rueda"),
    CAMBIO_LLANTA("Cambio de llanta"),
    CAMBIO_PASTILLAS_FRENO("Cambio de pastillas de freno"),
    REVISION_SUSPENSION("Revisión de suspensión"),
    REVISION_TRANSMISION("Revisión de transmisión"),
    RUIDO_DESCONOCIDO("Ruido desconocido"),
    ACCIDENTE("Accidente"),
    ACTUALIZACION_COMPONENTES("Actualización de componentes");

    private final String descripcion;

    MotivoReparacion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}