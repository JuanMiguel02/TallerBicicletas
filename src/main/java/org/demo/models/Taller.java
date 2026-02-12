package org.demo.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Taller {

    private String nombreUsuario;
    private String contrasenia;
    private ArrayList<Bicicleta> bicicletas = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Mecanico> mecanicos = new ArrayList<>();
    private ArrayList<OrdenReparacion> reparaciones = new ArrayList<>();

    public Taller(String nombreUsuario, String contrasenia) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    public void registrarCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void registrarBicicleta(Bicicleta bicicleta){
        this.bicicletas.add(bicicleta);
    }

    public void registrarMecanico(Mecanico mecanico){
        this.mecanicos.add(mecanico);
    }

    public void registrarOrdenReparacion(OrdenReparacion ordenReparacion){
        this.reparaciones.add(ordenReparacion);
    }

    public void crearOrden(Cliente cliente, Bicicleta bicicleta, Mecanico mecanico, LocalDateTime fechaYHora, String motivo, String diagnostico, int costo, int anio, String descripcionTrabajo){
        if(!hayMecanicoDisponible(mecanico, fechaYHora)){
            throw new IllegalArgumentException("El mecánico ya tiene un turno en esta hora");
        }
        OrdenReparacion orden = new OrdenReparacion( cliente,  bicicleta, mecanico,  fechaYHora, motivo, diagnostico,  costo,  anio, descripcionTrabajo);
        registrarOrdenReparacion(orden);

    }

    private boolean hayMecanicoDisponible(Mecanico mecanico, LocalDateTime fechaYHora){
        for(OrdenReparacion orden : reparaciones){
            if(orden.getMecanico().equals(mecanico) && orden.getFechaYHora().equals(fechaYHora)){
                return false;
            }
        }
        return true;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public ArrayList<OrdenReparacion> getReparaciones() {
        return reparaciones;
    }

    public void setReparaciones(ArrayList<OrdenReparacion> reparaciones) {
        this.reparaciones = reparaciones;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Bicicleta> getBicicletas() {
        return bicicletas;
    }

    public void setBicicletas(ArrayList<Bicicleta> bicicletas) {
        this.bicicletas = bicicletas;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "Taller{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", bicicletas=" + bicicletas +
                ", clientes=" + clientes +
                ", reparaciones=" + reparaciones +
                '}';
    }
}
