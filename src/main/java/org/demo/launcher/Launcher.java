package org.demo.launcher;

import javafx.application.Application;
import org.demo.models.Cliente;
import org.demo.models.Taller;

public class Launcher {
    public static void main(String[] args) {
        Application.launch(HelloApplication.class, args);

        //prueba (si sirve)
//        Taller taller = new Taller("admin", "1232141");
//        Cliente cliente = new Cliente("Keko Jones", "3123123", "2132131", "Armenia");
//        taller.registrarCliente(cliente);
//        System.out.println(taller.getClientes());
    }
}
