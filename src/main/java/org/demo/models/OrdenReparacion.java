package org.demo.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrdenReparacion {

    private UUID id;
    private Cliente cliente;
    private Bicicleta bicicleta;
    private Mecanico mecanico;
    private LocalDateTime fechaYHora;
    private String motivo;
    private String diagnostico;
    private int costo;
    private int anio;
    private String descripcionTrabajo;

    public OrdenReparacion(Cliente cliente, Bicicleta bicicleta, Mecanico mecanico, LocalDateTime fechaYHora, String motivo, String diagnostico, int costo, int anio, String descripcionTrabajo) {
        this.id = UUID.randomUUID();
        this.cliente = cliente;
        this.bicicleta = bicicleta;
        this.mecanico = mecanico;
        this.fechaYHora = fechaYHora;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.costo = costo;
        this.anio = anio;
        this.descripcionTrabajo = descripcionTrabajo;
    }

    public UUID getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(Bicicleta bicicleta) {
        this.bicicleta = bicicleta;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getDescripcionTrabajo() {
        return descripcionTrabajo;
    }

    public void setDescripcionTrabajo(String descripcionTrabajo) {
        this.descripcionTrabajo = descripcionTrabajo;
    }
}
