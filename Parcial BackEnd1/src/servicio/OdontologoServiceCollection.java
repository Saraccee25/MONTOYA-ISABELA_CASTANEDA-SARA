package servicio;

import dao.IDao;
import dao.implementacion.OdontologoDaoCollection;
import modelo.Odontologo;

import java.util.List;

public class OdontologoServiceCollection {
    private IDao<Odontologo> interfazDao;

    public OdontologoServiceCollection() {
        interfazDao = new OdontologoDaoCollection();
    }

    public Odontologo guardar(Odontologo odontologo){
        return interfazDao.guardar(odontologo);
    }

    public List<Odontologo> listar(){
        return interfazDao.listar();
    }
}
