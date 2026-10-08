package mx.generation.tareas;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class TareaTest {

    @Test
    void constructorRechazaTituloNull() {
        assertThrows(IllegalArgumentException.class,
            () -> new Tarea(null, "", Prioridad.BAJA, null));
    }

    @Test
    void constructorRechazaTituloVacio() {
        assertThrows(IllegalArgumentException.class,
            () -> new Tarea("", "", Prioridad.BAJA, null));
    }

    @Test
    void constructorRechazaTituloEnBlanco() {
        assertThrows(IllegalArgumentException.class,
            () -> new Tarea("   ", "", Prioridad.BAJA, null));
    }
}
