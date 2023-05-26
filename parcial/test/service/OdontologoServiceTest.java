package service;

import com.backend.parcial.model.Odontologo;
import com.backend.parcial.service.OdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

class OdontologoServiceTest {

    @Test
    public void listar_todos_los_odontologos_y_mostrarlos(){

        OdontologoService oService = new OdontologoService();
        Odontologo odontologo1 = new Odontologo(3,"Pedro", "de la Prieta");
        Odontologo odontologo2 = new Odontologo(4,"Rodrigo", "Ruiz");

        oService.registrarOdontologo(odontologo1);
        oService.registrarOdontologo(odontologo2);

        List<Odontologo> listaOdontologos = oService.listarOdontologos();

        Assertions.assertTrue(listaOdontologos.size() > 0);
    }


}
