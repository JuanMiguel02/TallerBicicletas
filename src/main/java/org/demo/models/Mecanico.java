package org.demo.models;

public class Mecanico extends Persona {

    private static int contadorMecanicos = 1;
    private String codigoInterno;
    private int sueldo;
    private EspecialidadMecanico especialidad;

    public Mecanico(String nombreCompleto, String telefono, String documento, String direccion, int sueldo, EspecialidadMecanico especialidad) {
        super(nombreCompleto, telefono, direccion, documento);
        this.codigoInterno = generarCodigo();
        this.sueldo = sueldo;
        this.especialidad = especialidad;
    }

    private String generarCodigo(){
        return "MEC-" + String.format("%03d", contadorMecanicos++);
    }

    public EspecialidadMecanico getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(EspecialidadMecanico especialidad) {
        this.especialidad = especialidad;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }
}
