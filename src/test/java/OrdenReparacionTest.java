import org.demo.models.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrdenReparacionTest {

    @Test
    void nuevaOrdenDebeIniciarEnCurso() {

        Cliente cliente = new Cliente("Paco Jones", "123214", "3213123", "Armenia, Quindío");
        Bicicleta bici = new Bicicleta("GW", TipoBicicleta.URBANA, "Verde", "ABC123", cliente, Year.of(2024));
        Mecanico mecanico = new Mecanico("Chino Moreno", "31312", "123123", "Armenia, Quindío", 50000, EspecialidadMecanico.SUSPENSION);

        OrdenReparacion orden = new OrdenReparacion(
                cliente, bici, mecanico,
                LocalDateTime.now(),
                "Diagnóstico",
                MotivoReparacion.PINCHAZO,
                50,
                "Cambio de cámara"
        );

        assertEquals(EstadoOrden.EN_CURSO, orden.getEstado());
    }

    @Test
    void debeCambiarEstadoACompletado() {

        Cliente cliente = new Cliente("Paco Jones", "123214", "3213123", "Armenia, Quindío");
        Bicicleta bici = new Bicicleta("GW", TipoBicicleta.URBANA, "Verde", "ABC123", cliente, Year.of(2024));
        Mecanico mecanico = new Mecanico("Chino Moreno", "31312", "123123", "Armenia, Quindío", 50000, EspecialidadMecanico.SUSPENSION);
        OrdenReparacion orden = new OrdenReparacion(
                cliente, bici, mecanico,
                LocalDateTime.now(),
                "Diagnóstico",
                MotivoReparacion.PINCHAZO,
                50,
                "Cambio de cámara"
        );

        orden.marcarCompletado();

        assertEquals(EstadoOrden.COMPLETADO, orden.getEstado());
    }

    @Test
    void debeGenerarUUID() {
        Cliente cliente = new Cliente("Paco Jones", "123214", "3213123", "Armenia, Quindío");
        Bicicleta bici = new Bicicleta("GW", TipoBicicleta.URBANA, "Verde", "ABC123", cliente, Year.of(2024));
        Mecanico mecanico = new Mecanico("Chino Moreno", "31312", "123123", "Armenia, Quindío", 50000, EspecialidadMecanico.SUSPENSION);

        OrdenReparacion orden = new OrdenReparacion(
                cliente, bici, mecanico,
                LocalDateTime.now(),
                "Diagnóstico",
                MotivoReparacion.PINCHAZO,
                50,
                "Cambio de cámara"
        );

        assertNotNull(orden.getId());
    }
}
