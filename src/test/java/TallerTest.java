import org.demo.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

public class TallerTest {
    private Taller taller;

    @BeforeEach
    void setUp() {
        taller = Taller.getInstancia();
        taller.limpiarDatos();
    }

    // ==============================
    // CLIENTES
    // ==============================

    @Test
    void registrarCliente_deberiaRegistrarCorrectamente() {
        Cliente cliente = new Cliente("Juan", "123", "1231", "armenia");

        boolean resultado = taller.registrarCliente(cliente);

        assertTrue(resultado);
        assertEquals(1, taller.getClientes().size());
    }

    @Test
    void registrarCliente_noDebePermitirDuplicados() {
        Cliente cliente1 = new Cliente("Juan", "123", "1231","armenia");
        Cliente cliente2 = new Cliente("Pedro", "123", "1231", "pereira"); // mismo documento

        taller.registrarCliente(cliente1);
        boolean resultado = taller.registrarCliente(cliente2);

        assertFalse(resultado);
        assertEquals(1, taller.getClientes().size());
    }

    @Test
    void eliminarCliente_deberiaEliminarCorrectamente() {
        Cliente cliente =  new Cliente("Juan", "123", "1231","armenia");
        taller.registrarCliente(cliente);

        boolean eliminado = taller.eliminarCliente(cliente);

        assertTrue(eliminado);
        assertEquals(0, taller.getClientes().size());
    }

    // ==============================
    // MECÁNICOS
    // ==============================

    @Test
    void registrarMecanico_deberiaRegistrarCorrectamente() {
        Mecanico mecanico = new Mecanico("Carlos", "456", "12312", "armenia", 40000,EspecialidadMecanico.SUSPENSION);

        boolean resultado = taller.registrarMecanico(mecanico);

        assertTrue(resultado);
        assertEquals(1, taller.getMecanicos().size());
    }

    @Test
    void existeMecanico_deberiaRetornarTrueSiExiste() {
        Mecanico mecanico = new Mecanico("Carlos", "456", "12312", "armenia", 40000,EspecialidadMecanico.SUSPENSION);
        taller.registrarMecanico(mecanico);

        assertTrue(taller.existeMecanico("12312"));
    }

    // ==============================
    // BICICLETAS
    // ==============================

    @Test
    void registrarBicicleta_deberiaRegistrarCorrectamente() {
        Cliente cliente = new Cliente("Juan", "123", "1231", "armenia");
        Bicicleta bici = new Bicicleta("GW", TipoBicicleta.URBANA, "Verde", "12321", cliente, Year.now());

        boolean resultado = taller.registrarBicicleta(bici);

        assertTrue(resultado);
        assertEquals(1, taller.getBicicletas().size());
    }

    @Test
    void existeBicicleta_deberiaRetornarTrueSiExiste() {
        Cliente cliente = new Cliente("Juan", "123", "1231", "armenia");
        Bicicleta bici = new Bicicleta("GW", TipoBicicleta.URBANA, "Verde", "12321", cliente, Year.now());
        taller.registrarBicicleta(bici);

        assertTrue(taller.existeBicicleta("12321"));
    }

    // ==============================
    // ÓRDENES DE REPARACIÓN
    // ==============================

    @Test
    void registrarOrdenReparacion_deberiaAgregarOrden() {
        Mecanico mecanico = new Mecanico("Carlos", "456", "12312", "armenia", 40000,EspecialidadMecanico.SUSPENSION);
        taller.registrarMecanico(mecanico);

        OrdenReparacion orden = new OrdenReparacion(
                mecanico,
                LocalDateTime.now()
        );

        boolean resultado = taller.registrarOrden(orden);

        assertTrue(resultado);
        assertEquals(1, taller.getReparaciones().size());
    }

    @Test
    void hayMecanicoDisponible_deberiaRetornarFalseSiEstaOcupado() {
        Mecanico mecanico = new Mecanico("Carlos", "456", "12312", "armenia", 40000,EspecialidadMecanico.SUSPENSION);
        taller.registrarMecanico(mecanico);

        LocalDateTime fecha = LocalDateTime.now();

        OrdenReparacion orden = new OrdenReparacion(mecanico, fecha);
        taller.registrarOrden(orden);

        boolean disponible = taller.hayMecanicoDisponible(mecanico, fecha);

        assertFalse(disponible);
    }

    @Test
    void hayMecanicoDisponible_deberiaRetornarTrueSiNoTieneOrden() {
        Mecanico mecanico = new Mecanico("Carlos", "456", "12312", "armenia", 40000,EspecialidadMecanico.SUSPENSION);
        taller.registrarMecanico(mecanico);

        boolean disponible = taller.hayMecanicoDisponible(mecanico, LocalDateTime.now());

        assertTrue(disponible);
    }

    // ==============================
    // ACTUALIZACIONES
    // ==============================

    @Test
    void actualizarCliente_deberiaActualizarCorrectamente() {
        Cliente cliente = new Cliente("Juan", "123", "1231", "armenia");
        taller.registrarCliente(cliente);

        cliente.setNombreCompleto("Paco");
        boolean resultado = taller.actualizarCliente(cliente);

        assertTrue(resultado);
    }

    @Test
    void actualizarCliente_deberiaLanzarExcepcionSiNoExiste() {
        Cliente cliente = new Cliente("Juan", "123", "1231", "armenia");

        assertThrows(IllegalArgumentException.class,
                () -> taller.actualizarCliente(cliente));
    }
}
