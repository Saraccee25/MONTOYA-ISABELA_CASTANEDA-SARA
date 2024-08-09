package servicio;

import dao.IDao;
import dao.implementacion.OdontologoDaoH2;
import modelo.Odontologo;

import java.util.List;

public class OdontologoService {

    private IDao<Odontologo> interfazDao;

    public OdontologoService() {
        interfazDao = new OdontologoDaoH2();
    }

    public Odontologo guardar(Odontologo odontologo){
        return interfazDao.guardar(odontologo);
    }

    public List<Odontologo> listar(){
        return interfazDao.listar();
    }
}
