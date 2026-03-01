package org.demo.models;

public interface IRegistroBicicletas {

    boolean registrarBicicleta(Bicicleta bicicleta);
    boolean eliminarBicicleta(Bicicleta bicicleta);
    boolean actualizarBicicleta(Bicicleta bicicleta);
    boolean existeBicicleta(String serie);
}
