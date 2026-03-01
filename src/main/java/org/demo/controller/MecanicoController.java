package org.demo.controller;

import javafx.collections.ObservableList;
import org.demo.models.Mecanico;
import org.demo.models.Taller;

public class MecanicoController {

    private final Taller taller = Taller.getInstancia();

    public boolean registrarMecanico(Mecanico mecanico){
        return taller.registrarMecanico(mecanico);
    }

    public boolean eliminarMecanico(Mecanico mecanico){
        return taller.eliminarMecanico(mecanico);
    }

    public ObservableList<Mecanico> listarMecanicos(){
        return taller.getMecanicos();
    }

    public boolean actualizarMecanico(Mecanico mecanico){
        return taller.actualizarMecanico(mecanico);
    }

}
