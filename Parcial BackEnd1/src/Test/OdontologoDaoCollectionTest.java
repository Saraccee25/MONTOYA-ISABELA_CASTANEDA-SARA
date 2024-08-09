package Test;

import dao.implementacion.OdontologoDaoCollection;
import modelo.Odontologo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoDaoCollectionTest {
    private OdontologoDaoCollection odontologoDAOCollection;

    @BeforeEach
    public void instanciar() {
        odontologoDAOCollection = new OdontologoDaoCollection();
    }

    @Test
    public void testListarOdontologos() {
        Odontologo odontologo1 = new Odontologo("Pérez", "Juan", "MAT001", 1);
        Odontologo odontologo2 = new Odontologo("Gómez", "Ana", "MAT002", 2);
        odontologoDAOCollection.guardar(odontologo1);
        odontologoDAOCollection.guardar(odontologo2);

        List<Odontologo> odontologos = odontologoDAOCollection.listar();

        assertEquals(2, odontologos.size());
        assertEquals("Juan", odontologos.get(0).getNombre());
        assertEquals("Ana", odontologos.get(1).getNombre());
    }
}
