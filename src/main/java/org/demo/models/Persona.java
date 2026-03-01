package org.demo.models;

public abstract class Persona {

    private static int contador = 0;
    private int id;
    private String nombreCompleto;
    private String telefono;
    private String documento;
    private String direccion;

    Persona(String nombreCompleto, String telefono, String direccion, String documento) {
        contador++;
        this.id = contador;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.documento = documento;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


}
