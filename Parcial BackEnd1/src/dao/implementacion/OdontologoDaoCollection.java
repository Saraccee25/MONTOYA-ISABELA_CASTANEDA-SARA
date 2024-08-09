package dao.implementacion;

import dao.IDao;
import modelo.Odontologo;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoCollection implements IDao<Odontologo> {
    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoCollection.class);
    private List<Odontologo> odontologos = new ArrayList<>();
    private int currentId = 1;

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        odontologo.setId(currentId++);
        odontologos.add(odontologo);
        LOGGER.info("Odontólogo guardado en memoria: " + odontologo.getNombre() + " " + odontologo.getApellido());
        return odontologo;
    }

    @Override
    public List<Odontologo> listar() {
        LOGGER.info("Listando todos los odontólogos en memoria");

        if (odontologos.isEmpty()) {
            LOGGER.info("No hay odontólogos guardados en memoria.");
        } else {
            LOGGER.info("Se listaron " + odontologos.size() + " odontólogos.");
            for (Odontologo odontologo : odontologos) {
                LOGGER.info("Odontólogo: ID = " + odontologo.getId() +
                        ", Nombre = " + odontologo.getNombre() +
                        ", Apellido = " + odontologo.getApellido() +
                        ", Matrícula = " + odontologo.getMatricula());
            }
        }

        return odontologos;
    }
}
