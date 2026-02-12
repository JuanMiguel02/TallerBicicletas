package org.demo.models;

public class Mecanico extends Persona {

    private int sueldo;
    private EspecialidadMecanico especialidad;

    public Mecanico(String nombreCompleto, String telefono, String documento, String direccion, int sueldo, EspecialidadMecanico especialidad) {
        super(nombreCompleto, telefono, direccion);
        this.sueldo = sueldo;
        this.especialidad = especialidad;
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
}
