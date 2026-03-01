package org.demo.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public final class Taller implements  IRegistroBicicletas, IRegistroClientes, IRegistroMecanicos, IRegistroOrdenes {

    private String nombreUsuario;
    private String contrasenia;
    private static Taller instancia;
    private final ArrayList<Bicicleta> bicicletas = new ArrayList<>();
    private final ArrayList<Cliente> clientes = new ArrayList<>();
    private final ArrayList<Mecanico> mecanicos = new ArrayList<>();
    private final ArrayList<OrdenReparacion> reparaciones = new ArrayList<>();

    private Taller() {
        this.nombreUsuario = "taller";
        this.contrasenia = "12345";
    }

    public static Taller getInstancia() {
        if(instancia == null){
            instancia = new Taller();
        }
        return instancia;
    }

    public boolean registrarCliente(Cliente cliente) {
        if(existeCliente(cliente.getDocumento())){
            return false;
        }
        return this.clientes.add(cliente);
    }

    public boolean registrarBicicleta(Bicicleta bicicleta) {
        if(existeBicicleta(bicicleta.getNumeroSerie())){
            return false;
        }
       return this.bicicletas.add(bicicleta);
    }

    public boolean registrarMecanico(Mecanico mecanico){
        if(existeMecanico(mecanico.getDocumento())){
            return false;
        }
        return this.mecanicos.add(mecanico);
    }

    public boolean eliminarCliente(Cliente cliente){
        return this.clientes.remove(cliente);
    }

    public boolean eliminarBicicleta(Bicicleta bicicleta){
        return this.bicicletas.remove(bicicleta);
    }

    public boolean eliminarMecanico(Mecanico mecanico){
        return this.mecanicos.remove(mecanico);
    }

    public boolean eliminarOrden(OrdenReparacion orden){
        return this.reparaciones.remove(orden);
    }

    public boolean actualizarCliente(Cliente cliente){
        for(int i = 0; i < this.clientes.size(); i++){
            if(cliente.equals(this.clientes.get(i))){
                this.clientes.set(i, cliente);
                return true;
            }
        }
        throw new IllegalArgumentException("Cliente no encontrado");
    }

    public boolean actualizarBicicleta(Bicicleta bicicleta){
        for(int i = 0; i < this.bicicletas.size(); i++){
            if(bicicleta.equals(this.bicicletas.get(i))){
                this.bicicletas.set(i, bicicleta);
                return true;
            }
        }
        throw new IllegalArgumentException("Bicicleta no encontrada");
    }

    public boolean actualizarMecanico(Mecanico mecanico){
        for(int i = 0; i < this.mecanicos.size(); i++){
            if(mecanico.equals(this.mecanicos.get(i))){
                this.mecanicos.set(i, mecanico);
                return true;
            }
        }
        throw new IllegalArgumentException(" Mecánico no encontrado");
    }

    public boolean actualizarOrden(OrdenReparacion orden){
        for(int i = 0; i < this.reparaciones.size(); i++){
            if(orden.equals(this.reparaciones.get(i))){
                this.reparaciones.set(i, orden);
                return true;
            }
        }
        throw new IllegalArgumentException("Orden no encontrada");
    }

    public boolean registrarOrden(OrdenReparacion ordenReparacion){
        return this.reparaciones.add(ordenReparacion);
    }

    public boolean hayMecanicoDisponible(Mecanico mecanico, LocalDateTime fechaYHora){
        for(OrdenReparacion orden : reparaciones){
            if(orden.getMecanico().equals(mecanico) && orden.getFechaYHora().equals(fechaYHora)){
                return false;
            }
        }
        return true;
    }

    public boolean existeMecanico(String documento){
        for(Mecanico mecanico : this.mecanicos){
            if(mecanico.getDocumento().equals(documento)){
                return true;
            }
        }
        return false;
    }

    public boolean existeCliente(String documento){
        for(Cliente cliente : this.clientes){
            if(cliente.getDocumento().equals(documento)){
                return true;
            }
        }
        return false;
    }

    public boolean existeBicicleta(String serie) {
        for (Bicicleta bicicleta: this.bicicletas) {
            if (bicicleta.getNumeroSerie().equals(serie)) {
                return true;
            }
        }
        return false;
    }

    public void limpiarDatos() {
        bicicletas.clear();
        clientes.clear();
        mecanicos.clear();
        reparaciones.clear();
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

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Bicicleta> getBicicletas() {
        return bicicletas;
    }

    public ArrayList<Mecanico> getMecanicos() {
        return mecanicos;
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
