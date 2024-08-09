import dao.BD;
import modelo.Odontologo;
import servicio.OdontologoService;
import servicio.OdontologoServiceCollection;

public class Main {
    public static void main(String[] args) {

        //Usando H2
        BD.correrSQLScript("src/bd/create_tables.sql");
        OdontologoService odontologoService = new OdontologoService();

        Odontologo odontologo1 = new Odontologo("García", "Luis", "MAT1234");
        Odontologo odontologo2 = new Odontologo("Martínez", "Ana", "MAT5678");

        odontologoService.guardar(odontologo1);
        odontologoService.guardar(odontologo2);

        odontologoService.listar();

        //Usando ArrayList
        Odontologo odontologo3 = new Odontologo("Rodríguez", "Carlos", "MAT9876");
        Odontologo odontologo4 = new Odontologo("López", "María", "MAT5432");

        OdontologoServiceCollection odontologoServiceCollection = new OdontologoServiceCollection();

        odontologoServiceCollection.guardar(odontologo3);
        odontologoServiceCollection.guardar(odontologo4);

        odontologoServiceCollection.listar();

    }
}
