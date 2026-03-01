package org.demo.models;

import java.time.LocalDateTime;

public interface IRegistroMecanicos {

    boolean registrarMecanico(Mecanico mecanico);
    boolean eliminarMecanico(Mecanico mecanico);
    boolean actualizarMecanico(Mecanico mecanico);

}
