package dao.implementacion;

import dao.BD;
import dao.IDao;
import modelo.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        LOGGER.info("Comenzamos a persistir un odontólogo");

        Connection connection = null;
        try {
            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO ODONTOLOGOS (APELLIDO, NOMBRE, MATRICULA) VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, odontologo.getApellido());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getMatricula());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            while (rs.next()) {
                odontologo.setId(rs.getInt(1));
                LOGGER.info("Odontólogo guardado: " + odontologo.getNombre() + " " + odontologo.getApellido());
            }

        } catch (Exception e) {
            LOGGER.error("Error al guardar odontólogo", e);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Error al cerrar la conexión", ex);
            }
        }
        return odontologo;
    }

    @Override
    public List<Odontologo> listar() {
        LOGGER.info("Comenzamos a listar los odontólogos");

        Connection connection = null;
        List<Odontologo> listaOdontologos = new ArrayList<>();

        try {
            connection = BD.getConnection();

            String sqlOdontologos = "SELECT * FROM ODONTOLOGOS";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlOdontologos);

            while (rs.next()) {
                Odontologo odontologo = new Odontologo();
                odontologo.setId(rs.getInt("ID"));
                odontologo.setApellido(rs.getString("APELLIDO"));
                odontologo.setNombre(rs.getString("NOMBRE"));
                odontologo.setMatricula(rs.getString("MATRICULA"));

                listaOdontologos.add(odontologo);

                LOGGER.info("Odontólogo encontrado: ID = " + odontologo.getId() +
                        ", Apellido = " + odontologo.getApellido() +
                        ", Nombre = " + odontologo.getNombre() +
                        ", Matrícula = " + odontologo.getMatricula());
            }

            if (listaOdontologos.isEmpty()) {
                LOGGER.info("No se encontró ningún odontólogo.");
            } else {
                LOGGER.info("Se listaron " + listaOdontologos.size() + " odontólogos.");
            }

        } catch (Exception e) {
            LOGGER.error("Error al listar los odontólogos", e);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Error al cerrar la conexión", ex);
            }
        }

        return listaOdontologos;
    }
}
