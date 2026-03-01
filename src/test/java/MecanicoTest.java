import org.demo.models.EspecialidadMecanico;
import org.demo.models.Mecanico;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MecanicoTest {

    @Test
    void debeGenerarCodigoCorrecto() {
        Mecanico m1 = new Mecanico("Keko Jones", "123123", "34123", "Armenia", 3999, EspecialidadMecanico.SUSPENSION);
        assertEquals("MEC-001", m1.getCodigoInterno());

        Mecanico m2 = new Mecanico("Pancho Villa", "3113", "121331", "Pereira", 313123, EspecialidadMecanico.BICICLETAELECTRICA);
        assertEquals("MEC-002", m2.getCodigoInterno());
    }
}
