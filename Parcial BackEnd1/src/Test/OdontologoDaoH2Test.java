package Test;

import dao.BD;
import dao.implementacion.OdontologoDaoH2;
import modelo.Odontologo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoDaoH2Test {

    private OdontologoDaoH2 odontologoDaoH2;

    @BeforeEach
    public void crearTabla() throws Exception {
        odontologoDaoH2 = new OdontologoDaoH2();

        Connection connection = BD.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("DROP TABLE IF EXISTS ODONTOLOGOS");
        statement.execute("CREATE TABLE ODONTOLOGOS (ID INT PRIMARY KEY AUTO_INCREMENT, APELLIDO VARCHAR(30), NOMBRE VARCHAR(30), MATRICULA VARCHAR(50))");
        statement.execute("INSERT INTO ODONTOLOGOS (APELLIDO, NOMBRE, MATRICULA) VALUES ('García', 'Luis', 'MAT1234')");
        statement.execute("INSERT INTO ODONTOLOGOS (APELLIDO, NOMBRE, MATRICULA) VALUES ('Martínez', 'Ana', 'MAT5678')");
        statement.execute("INSERT INTO ODONTOLOGOS (APELLIDO, NOMBRE, MATRICULA) VALUES ('Rodríguez', 'Carlos', 'MAT9876')");
        statement.execute("INSERT INTO ODONTOLOGOS (APELLIDO, NOMBRE, MATRICULA) VALUES ('López', 'María', 'MAT5432')");

        connection.close();
    }

    @AfterEach
    public void borrarTabla() throws Exception {
        Connection connection = BD.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS ODONTOLOGOS");
        connection.close();
    }

    @Test
    public void testListarOdontologos() {
        List<Odontologo> odontologos = odontologoDaoH2.listar();

        assertFalse(odontologos.isEmpty(), "La lista de odontólogos no debería estar vacía");

        assertEquals(4, odontologos.size(), "El número de odontólogos listados debería ser 4");

        Odontologo odontologo1 = odontologos.get(0);
        assertEquals("García", odontologo1.getApellido());
        assertEquals("Luis", odontologo1.getNombre());
        assertEquals("MAT1234", odontologo1.getMatricula());
    }


}